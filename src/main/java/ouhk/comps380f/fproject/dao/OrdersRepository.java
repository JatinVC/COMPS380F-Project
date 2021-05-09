/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.fproject.model.Orders;

/**
 *
 * @author Lkii
 */
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
   
}
