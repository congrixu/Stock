package com.rxv5.platform.result;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.struts2.dispatcher.mapper.ActionMapper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionProxyFactory;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.XWorkException;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.TextParseUtil;
import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class ChainResult implements Result {

	protected ActionMapper actionMapper;

	@Inject
	public void setActionMapper(ActionMapper mapper) {
		this.actionMapper = mapper;
	}

	/**
	 * The result parameter name to set the name of the action to chain to.
	 */
	public static final String DEFAULT_PARAM = "actionName";

	/**
	 * The action context key to save the chain history.
	 */
	private static final String CHAIN_HISTORY = "CHAIN_HISTORY";

	/**
	 * The result parameter name to set the name of the action to chain to.
	 */
	public static final String SKIP_ACTIONS_PARAM = "skipActions";

	private ActionProxy proxy;
	private String actionName;

	private String namespace;

	private String methodName;

	/**
	 * The list of actions to skip.
	 */
	private String skipActions;

	private ActionProxyFactory actionProxyFactory;

	public ChainResult() {
		super();
	}

	public ChainResult(String namespace, String actionName, String methodName) {
		this.namespace = namespace;
		this.actionName = actionName;
		this.methodName = methodName;
	}

	public ChainResult(String namespace, String actionName, String methodName, String skipActions) {
		this.namespace = namespace;
		this.actionName = actionName;
		this.methodName = methodName;
		this.skipActions = skipActions;
	}

	/**
	 * @param actionProxyFactory the actionProxyFactory to set
	 */
	@Inject
	public void setActionProxyFactory(ActionProxyFactory actionProxyFactory) {
		this.actionProxyFactory = actionProxyFactory;
	}

	/**
	 * Set the action name.
	 *
	 * @param actionName The action name.
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * sets the namespace of the Action that we're chaining to.  if namespace
	 * is null, this defaults to the current namespace.
	 *
	 * @param namespace the name of the namespace we're chaining to
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	/**
	 * Set the list of actions to skip.
	 * To test if an action should not throe an infinite recursion,
	 * only the action name is used, not the namespace.
	 *
	 * @param actions The list of action name separated by a white space.
	 */
	public void setSkipActions(String actions) {
		this.skipActions = actions;
	}

	public void setMethod(String method) {
		this.methodName = method;
	}

	public ActionProxy getProxy() {
		return proxy;
	}

	/**
	 * Get the XWork chain history.
	 * The stack is a list of <code>namespace/action!method</code> keys.
	 */
	@SuppressWarnings("unchecked")
	public static LinkedList<String> getChainHistory() {
		LinkedList<String> chainHistory = (LinkedList<String>) ActionContext.getContext().get(CHAIN_HISTORY);
		//  Add if not exists
		if (chainHistory == null) {
			chainHistory = new LinkedList<String>();
			ActionContext.getContext().put(CHAIN_HISTORY, chainHistory);
		}

		return chainHistory;
	}

	/**
	 * @param invocation the DefaultActionInvocation calling the action call stack
	 */
	public void execute(ActionInvocation invocation) throws Exception {
		// if the finalNamespace wasn't explicitly defined, assume the current one
		if (this.namespace == null) {
			this.namespace = invocation.getProxy().getNamespace();
		}
		ValueStack stack = ActionContext.getContext().getValueStack();
		String finalActionName = (String) ActionContext.getContext().get("location");
		int index = finalActionName.lastIndexOf("/");
		String finalNamespace = finalActionName.substring(0, index);
		finalActionName = finalActionName.substring(index + 1);
		if (index < 0) {
			finalNamespace = TextParseUtil.translateVariables(namespace, stack);
		}
		//        String finalActionName = TextParseUtil.translateVariables(actionName, stack);
		//        String finalMethodName = this.methodName != null
		//                ? TextParseUtil.translateVariables(this.methodName, stack)
		//                : null;

		String finalMethodName = finalActionName;
		if (isInChainHistory(finalNamespace, finalActionName, finalMethodName)) {
			addToHistory(finalNamespace, finalActionName, finalMethodName);
			throw new XWorkException("Infinite recursion detected: " + ChainResult.getChainHistory().toString());
		}

		if (ChainResult.getChainHistory().isEmpty() && invocation != null && invocation.getProxy() != null) {
			addToHistory(finalNamespace, invocation.getProxy().getActionName(), invocation.getProxy().getMethod());
		}
		addToHistory(finalNamespace, finalActionName, finalMethodName);

		HashMap<String, Object> extraContext = new HashMap<String, Object>();
		extraContext.put(ActionContext.VALUE_STACK, ActionContext.getContext().getValueStack());
		extraContext.put(ActionContext.PARAMETERS, ActionContext.getContext().getParameters());
		extraContext.put(CHAIN_HISTORY, ChainResult.getChainHistory());

		proxy = actionProxyFactory.createActionProxy(finalNamespace, finalActionName, finalMethodName, extraContext);
		proxy.execute();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final ChainResult that = (ChainResult) o;

		if (actionName != null ? !actionName.equals(that.actionName) : that.actionName != null)
			return false;
		if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null)
			return false;
		if (namespace != null ? !namespace.equals(that.namespace) : that.namespace != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		result = (actionName != null ? actionName.hashCode() : 0);
		result = 31 * result + (namespace != null ? namespace.hashCode() : 0);
		result = 31 * result + (methodName != null ? methodName.hashCode() : 0);
		return result;
	}

	private boolean isInChainHistory(String namespace, String actionName, String methodName) {
		LinkedList<? extends String> chainHistory = ChainResult.getChainHistory();

		if (chainHistory == null) {
			return false;
		} else {
			//  Actions to skip
			Set<String> skipActionsList = new HashSet<String>();
			if (skipActions != null && skipActions.length() > 0) {
				ValueStack stack = ActionContext.getContext().getValueStack();
				String finalSkipActions = TextParseUtil.translateVariables(this.skipActions, stack);
				skipActionsList.addAll(TextParseUtil.commaDelimitedStringToSet(finalSkipActions));
			}
			if (!skipActionsList.contains(actionName)) {
				//  Get if key is in the chain history
				return chainHistory.contains(makeKey(namespace, actionName, methodName));
			}

			return false;
		}
	}

	private void addToHistory(String namespace, String actionName, String methodName) {
		List<String> chainHistory = ChainResult.getChainHistory();
		chainHistory.add(makeKey(namespace, actionName, methodName));
	}

	private String makeKey(String namespace, String actionName, String methodName) {
		if (null == methodName) {
			return namespace + "/" + actionName;
		}

		return namespace + "/" + actionName + "!" + methodName;
	}

}
