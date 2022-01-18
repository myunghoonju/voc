# vocRefund
Scenario:: Handling client's complain and charging money to who's responsible.  
  
// 1. voc등록   
// req  
// voc/save  
{  
  "client_id": "client_saruga",  
  "partner_id": "partner_hansol",  
  "description": "damaged_parcel",  
  "product_id": "product111",  
  "partner_emp_id": "emp111",  
  "receiver": "partner_hansol"    
}  
// res  
1  
  
// 2. 운송사 귀책의 경우 페널티 등록   
// req  
// penalty/save  
{  
  "accident": "damaged_parcel",  
  "partner_emp_id": "emp111",  
  "claim_yn": "n",  
  "confirmed_yn": "n",  
  "charge_amt": 10000,  
  "voc_id": 1    
}  
// res  
1  
  
// 3. 기사님 push 확인 및 사인  
// req  
// penalty/accept?vocId=1  
// res  
1  
  
// 4. 배상등록 전 의의제기 여부와 기사님확인 여부  
// req  
// penalty/check?vocId=1  
// res  
true  
   
// 5. 배상등록 및 페널티 완료처리(pending -> accepted)  
// req  
// refund/save  
{  
  "partner_emp_id": "emp111",  
  "description": "damaged_parcel",  
  "penalty_id": 1,  
  "charge_amt": 10000,  
  "voc_id": 1  
}  
// res  
1  
  
// 6. 목록 api   
// voc/list (voc와 페널티 정보를 조인하여 함께표시)  
// refund/list (배상목록)  
  
