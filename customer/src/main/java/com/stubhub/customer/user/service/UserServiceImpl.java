package com.stubhub.customer.user.service;

import com.stubhub.common.Response;
import com.stubhub.customer.AccountOrPwdIncorrectException;
import com.stubhub.customer.user.AddTicketReq;
import com.stubhub.customer.user.Ticket;
import com.stubhub.customer.user.entity.UserEntity;
import com.stubhub.customer.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("LoginService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    private RestTemplate restTemplate = new RestTemplate();

    public UserEntity login(String username, String pwd) {

        UserEntity user = userRepository.findByUsername(username);

        if (user == null || !user.getPassword().equals(pwd)) {
            throw new RuntimeException("account or password are incorrect!");
        }

        return user;
    }

    public UserEntity findUserById(Long id) {

        Optional<UserEntity> userEntity = userRepository.findById(id);

        if (!userEntity.isPresent()) {
            throw new AccountOrPwdIncorrectException("Account not found");
        }
        return userEntity.orElse(null);
    }

    @Override
    public List<Ticket> listMyTickets(Long userId) {
        ResponseEntity<Response> res = restTemplate.getForEntity("http://localhost:8082/tickets?userId="+userId,Response.class);

        List<Ticket> tickets = (List<Ticket>) res.getBody().getData();
        return tickets;
    }

    @Override
    public void addTicket(AddTicketReq addTicketReq) {
        ResponseEntity re = restTemplate.postForEntity("http://localhost:9099/inventory/add",addTicketReq,Response.class);
        LOGGER.info(String.valueOf(re.getStatusCodeValue()));
    }

    @Override
    public void addTicketFailed(AddTicketReq addTicketReq) {
        ResponseEntity re = restTemplate.postForEntity("http://localhost:9099/inventory/add/failed",addTicketReq,Response.class);
        LOGGER.info(String.valueOf(re.getStatusCodeValue()));
    }
}
