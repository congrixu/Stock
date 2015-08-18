package com.rxv5.stock;

public class Constant {
	/**
	 * 每页默认显示记录数
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/**
	 * 采购单
	 * @author congrixu
	 */
	public enum PurchaseEnum {

		onTheway("1", "在途"), inToLib("2", "入库"), delete("0", "删除");
		private String id;
		private String text;

		PurchaseEnum(String id, String text) {
			this.id = id;
			this.text = text;
		}

		public static PurchaseEnum byId(String id) {
			for (PurchaseEnum constant : PurchaseEnum.values()) {
				if (id.equals(constant.getId()))
					return constant;
			}
			throw new IllegalArgumentException("No PurchaseEnum constant with id=" + id + " exist.");
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

	}

	/**
	 * 销售单
	 * @author congrixu
	 */
	public enum SalesEnum {

		delete("0", "删除"), undelete("1", "未删除");
		private String id;
		private String text;

		SalesEnum(String id, String text) {
			this.id = id;
			this.text = text;
		}

		public static SalesEnum byId(String id) {
			for (SalesEnum constant : SalesEnum.values()) {
				if (id.equals(constant.getId()))
					return constant;
			}
			throw new IllegalArgumentException("No SalesEnum constant with id=" + id + " exist.");
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

	}

}
