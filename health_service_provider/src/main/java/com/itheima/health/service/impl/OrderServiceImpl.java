package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.common.MessageConst;
import com.itheima.health.dao.MemberDao;
import com.itheima.health.dao.OrderDao;
import com.itheima.health.dao.OrderSettingDao;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Member;
import com.itheima.health.pojo.Order;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderService;
import com.itheima.health.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    @Transactional
    @Override
    public Result addOrder(Map<String, String> map) {
        Date dateOrderDate = null;
        try {
            dateOrderDate = DateUtils.parseString2Date(map.get("orderDate"));
        }catch (Exception e){
            return new Result(false,"预约时间不正确");
        }

        OrderSetting orderSetting = orderSettingDao.findByOrderDate(map.get("orderDate"));

        if (orderSetting == null){
            return new Result(false,MessageConst.GET_ORDERSETTING_FAIL);
        }
        if(orderSetting.getReservations()>=orderSetting.getNumber()){
            return new Result(false,MessageConst.ORDER_FULL);
        }

        Member member = memberDao.findByTelephone(map.get("telephone"));
        if (member == null){
            member = new Member();
            member.setName(map.get("name"));
            member.setSex(map.get("sex"));
            member.setIdCard(map.get("idCard"));
            member.setPhoneNumber(map.get("telephone"));
            member.setRegTime(new Date());

            memberDao.add(member);
        }else {

            Order order = new Order();
            order.setMemberId(member.getId());
            order.setOrderDate(dateOrderDate);
            List<Order> orderList = orderDao.findByCondition(order);
            if (orderList != null && orderList.size()>0){
                return new Result(false,MessageConst.HAS_ORDERED);
            }


        }

        Order order = new Order();
        order.setOrderDate(dateOrderDate);
        order.setMemberId(member.getId());
        order.setSetmealId(Integer.parseInt(map.get("setmealId")));
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setOrderType(map.get("orderType"));
        orderDao.add(order);
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingDao.editReservationByOrderDate(orderSetting);

        HashMap m = new HashMap<>();
        m.put("id",order.getId()+"");
        return new Result(true, MessageConst.ORDER_SUCCESS,m);
    }

    @Override
    public Map<String, Object> findByid4OrderDetail(Integer id) {
        Map<String,Object> map = orderDao.findById4Detail(id);
        String strDate= "";
        try {
            strDate = DateUtils.parseDate2String((Date) map.get("orderDate"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("orderDate",strDate);
       /* map.put("member","test");
        map.put("setmeal","入职套餐");
        map.put("orderDate","2019-07-23");
        map.put("orderType","微信预约");*/
        return map;
    }
}
