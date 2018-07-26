package com.shengruitn.dao;

import com.shengruitn.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    List<OrderMaster> findByBuyerName(String buyerName );
    List<OrderMaster> findByBuyerPhone(String buyerPhone );

    /* 正式 */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
