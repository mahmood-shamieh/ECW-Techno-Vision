//package com.example.momoPlans;
//
//public class Test {
//}
//
//public class MomoBalanceResponse extends APIResponse {
//    private static final long serialVersionUID = -6248778532148160111L;
//    @JsonProperty("statusCode")
//    private String statusCode = null;
//    @JsonProperty("data")
//    private MomoBalanceDetail momoBalanceDetail = null;
//}
//
//@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class MomoBalanceDetail {
//    @JsonProperty("balance")
//    private Amount balance;
//    @JsonProperty("positiveReservations")
//    private Amount positiveReservations;
//    @JsonProperty("negativeReservations")
//    private Amount negativeReservations;
//    @JsonProperty("loyaltyInformation")
//    private LoyaltyInformation loyaltyInformation;
//    @JsonProperty("overdraftInformation")
//    private OverdraftInformation overdraftInformation;
//}
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Amount {
//    private String type;
//    private String value;
//    private String unit;
//
//}
//
//@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class LoyaltyInformation {
//    private LoyaltyBalance loyaltyBalance;
//}
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@Data
//public class LoyaltyBalance {
//    private Amount generatedAmount;
//    private Amount consumedAmount;
//    private Amount newBalance;
//}
//
//@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class OverdraftInformation {
//    private Amount balance;
//    private Amount limit;
//    private Amount accessFee;
//    private Amount interest;
//}
//
//
//
//
//
//
//
//
//
//
//
