package com.bigeye.mycust.appdirect.enums;

public enum EventResultMessageEnum {

		SUBSCRIPTION_ORDER_OK("Subscription created!"),
		
		SUBSCRIPTION_CHANGE_OK("Subscription changed!");
		
		private String value;
		
		private EventResultMessageEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
		
}
