select id,uuid,id_process,POS_SELECTED,sellerplace_code  
from t_application where id=435665029;

SELECT * FROM t_application where id between 434666636 and 435666636 order by cdate desc;

select * from t_vendor_order where ID_APPLICATION=435665029;
select * from t_tracking where ID_APPLICATION=435665029;

select * from t_application where id=435667605;
SELECT * FROM T_CONTRACT WHERE ID_APPLICATION = '435662515';

select * from t_application where uuid='19297e59-0130-4d00-8214-2ddde74f2632';

select * from t_timeout_notification where id_application =435662478;

SELECT * FROM t_outbound_result where id_application=435667626 order by id desc;

select * from t_applicant where idcard_nbr='310106198908072892';
select * from t_timeout_notification order by id desc;

update t_applicant set id_province =64 where  id_application=435661237;
update t_timeout_notification set status =1 where id_application =435661237;
commit;

explain plan for select distinct t.id_application from t_vendor v 
    inner join t_vendor_order o on v.id = o.id_vendor  
    inner join t_trx_journal t on o.id_application = t.id_application 
    where v.status = 1 and t.id_status = 12 
    and v.vendor_code = 12 
    and o.cdate >= to_date('2019-09-24T13:36:13.105') 
    and o.cdate <= to_date('2019-09-24T13:36:13.105');
    
explain plan for select * from t_vendor_order where cdate <=to_date(sysdate,'yyyy-mm-dd hh24:mi:ss');
select * from table(dbms_xplan.display);