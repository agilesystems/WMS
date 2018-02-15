/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Store;
import com.xnet.wms.entity.Transfer;
import java.util.Collection;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author Muhammad
 */
public interface TransferService {

    boolean save(Transfer transfer);

    boolean delete(Transfer transfer);

    Collection<Transfer> getAll();

    Collection<Transfer> getByStoreid(Store from, Store to);
    
    Collection<Transfer> getBydate(Date from , Date to);
}
