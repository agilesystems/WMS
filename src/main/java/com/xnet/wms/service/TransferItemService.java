/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Item;
import com.xnet.wms.entity.TransferItem;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 *
 * @author Muhammad
 */
public interface TransferItemService {

    boolean save(TransferItem TransferItem);

    boolean delete(TransferItem TransferItem);

    Collection<TransferItem> getByItemid(Item item);
}
