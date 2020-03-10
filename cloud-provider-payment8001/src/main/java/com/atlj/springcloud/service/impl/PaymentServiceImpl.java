package com.atlj.springcloud.service.impl;

import com.atlj.springcloud.dao.PaymentDao;
import com.atlj.springcloud.entities.Payment;
import com.atlj.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class PaymentServiceImpl implements PaymentService {

        @Resource
        private PaymentDao paymentDao;

        @Override
        public Payment getPaymentById(Long id) {
            return paymentDao.getPaymentById(id);
        }

        @Override
        public int create(Payment payment) {
            return paymentDao.create(payment);
        }
}
