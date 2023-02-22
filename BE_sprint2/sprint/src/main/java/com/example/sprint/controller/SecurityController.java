package com.example.sprint.controller;

import com.example.sprint.dto.CustomerDto;
import com.example.sprint.dto.JwtResponse;
import com.example.sprint.dto.SignInForm;
import com.example.sprint.jwt.jwt.JwtProvider;
import com.example.sprint.jwt.jwt.JwtTokenFilter;
import com.example.sprint.jwt.user_principal.AccountPrincipal;
import com.example.sprint.model.Account;
import com.example.sprint.model.Customer;
import com.example.sprint.model.Role;
import com.example.sprint.model.RoleName;
import com.example.sprint.service.ICustomerService;
import com.example.sprint.service.IRoleService;
import com.example.sprint.service.impl.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping(value = "/signup")
    public ResponseEntity<Customer> register(@Valid @RequestBody CustomerDto customerDto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>((Customer) bindingResult.getFieldErrors(),
                    HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        Account account = new Account();
        account.setName(customerDto.getName());
        account.setEmail(customerDto.getEmail());
        account.setAvatar("hello");
        account.setEncryptPassword(passwordEncoder.encode(customerDto.getEncryptPassword()));
        Set<Role> roles = new HashSet<>();
        Role customerRole = roleService.findByName(RoleName.USER).orElse(new Role());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nhan09skbk@gmail.com");
        message.setTo(customerDto.getEmail());
        String mailSubject = "UQN Wifi Store đã gửi một tin nhắn";
        String mailContent = "Người gửi: " + "UQN Wifi Store" + "\n";
        mailContent += "Sender E-mail: " + "nhan09skbk@gmail.com" + "\n";
        mailContent += "Subject: " + "Thư phản hồi" + "\n";
        mailContent += "Content: " + "Chào mừng quí khách đã đến với UQN Wifi Store" + "\n";
        mailContent += "Username: " + customerDto.getEmail() + "\n";
        mailContent += "Password: " + customerDto.getEncryptPassword() + "\n";
        mailContent += "Content: " + "Vui lòng đăng nhập để tiếp tục." + "\n";
        message.setSubject(mailSubject);
        message.setText(mailContent);
        mailSender.send(message);

        roles.add(customerRole);
        account.setRoleSet(roles);
        accountService.save(account);
        customer.setAccount(account);
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getEmail(), signInForm.getEncryptPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);
        AccountPrincipal accountPrincipal = (AccountPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrincipal.getName(),
                accountPrincipal.getAuthorities(),
                accountPrincipal.getId(),
                accountPrincipal.getEmail(),
                accountPrincipal.getAvatar()));
    }
}
