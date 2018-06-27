package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.Ticket;
import com.ycjcjy.gene.service.TicketService;
import com.ycjcjy.gene.dao.TicketDao;

@Service
public class TicketServiceImpl extends BaseBiz<Ticket, TicketDao> implements TicketService{
}