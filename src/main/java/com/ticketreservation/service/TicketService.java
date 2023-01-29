package com.ticketreservation.service;

import com.ticketreservation.config.RabbitMQConfiguration;
import com.ticketreservation.controller.UserController;
import com.ticketreservation.converter.TicketConverter;
import com.ticketreservation.enums.UserType;
import com.ticketreservation.model.Ticket;
import com.ticketreservation.model.User;
import com.ticketreservation.repository.TicketRepository;
import com.ticketreservation.request.TicketRequest;
import com.ticketreservation.response.TicketResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private static final int MAX_INDIVIDUAL_TİCKET_SIZE = 5;
    private static final int MAX_CORPORATE_TİCKET_SIZE = 20;
    private static final int MAX_INDIVIDUAL_MALE_QUANTITY = 2;



    private UserService userService;
    private TicketRepository ticketRepository;
    private RabbitTemplate rabbitTemplate;
    private RabbitMQConfiguration rabbitMQConfiguration;
    private TicketConverter ticketConverter;

    private Logger logger = Logger.getLogger(UserController.class.getName());

    public TicketService(UserService userService,TicketRepository ticketRepository,RabbitTemplate rabbitTemplate, RabbitMQConfiguration rabbitMQConfiguration,TicketConverter ticketConverter){
        this.userService = userService;
        this.ticketRepository = ticketRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQConfiguration = rabbitMQConfiguration;
        this.ticketConverter=ticketConverter;
    }

    public TicketResponse create(TicketRequest ticketRequest) throws Exception{
        Optional<User>foundUser = userService.getById(ticketRequest.getNo());

        if (foundUser.isPresent()){
            if (UserType.INDIVIDUAL.equals(foundUser.get().getUserType())){
                List<Ticket> ticketList = ticketRepository.findAllByUserId(foundUser.get().getId());
                if (MAX_INDIVIDUAL_TİCKET_SIZE<=ticketList.size()&&MAX_INDIVIDUAL_MALE_QUANTITY<= ticketRequest.getMaleQuantity()){
                    logger.log(Level.WARNING,"Individual users can purchase 2 tickets as the number of men and 5 tickets as the maximum number of tickets. userID : {0}");
                        foundUser.get().getId();
                    throw new Exception("Individual users can purchase 2 tickets as the number of men and 5 tickets as the maximum number of tickets.");
                }
            }
            if (UserType.CORPORATE.equals(foundUser.get().getUserType())){
                List<Ticket> ticketList = ticketRepository.findAllByUserId(foundUser.get().getId());
                if (MAX_CORPORATE_TİCKET_SIZE<=ticketList.size()){
                    logger.log(Level.WARNING,"Corporate user can only buy 20 tickets. userID : {0}");
                        foundUser.get().getId();
                    throw new Exception("Corporate user can only buy 20 tickets.");
                }
            }
        }
        else{
            throw new IllegalArgumentException("\"user does not exist\"");
        }

        Ticket ticket = ticketConverter.convert(ticketRequest);

        if (foundUser.isPresent()){
            ticket.setUser(foundUser.get());
        }
        Ticket savedTicket =ticketRepository.save(ticket);
        //SMS send
        return ticketConverter.convert(savedTicket);
    }

    public List<TicketResponse> getAllById(int id) {
        return ticketRepository.findAllByUserId(id).stream().map(ticketConverter::convert).collect(Collectors.toList());
    }

    public long getAllTickets(Long totalSales){
        Logger logger = Logger.getLogger(TicketService.class.getName());
        logger.log(Level.INFO,"Total ticket sales: {0}");
        long count = ticketRepository.countByTotalTicketSales(totalSales);
        logger.log(Level.INFO, "Total ticket counts from db: {0}", count);
        return count;
    }
    public long getTotalSalesRevenue(Long totalPrice){
        Logger logger = Logger.getLogger(TicketService.class.getName());
        logger.log(Level.INFO,"Total sales revenue: {0}");
        long count = ticketRepository.countByTotalPrice(totalPrice);
        logger.log(Level.INFO, "Total sales revenue from db: {0}", count);
        return count;
    }

}
