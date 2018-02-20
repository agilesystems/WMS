/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.repository;

import com.xnet.wms.entity.TransferItem;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Muhammad
 */
@Repository
public interface TransferItemRepository extends JpaRepository<TransferItem, Integer> {

}
