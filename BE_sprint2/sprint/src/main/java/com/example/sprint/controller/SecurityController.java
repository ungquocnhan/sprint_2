package com.example.sprint.controller;

import com.example.sprint.dto.CustomerDto;
import com.example.sprint.dto.GetIdCustomer;
import com.example.sprint.dto.JwtResponse;
import com.example.sprint.dto.SignInForm;
import com.example.sprint.jwt.jwt.JwtProvider;
import com.example.sprint.jwt.jwt.JwtTokenFilter;
import com.example.sprint.jwt.user_principal.AccountPrincipal;
import com.example.sprint.model.*;
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
import java.util.Optional;
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
    @Autowired
    private CartRepository cartRepository;

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
        account.setAvatar("https://upload.wikimedia.org/wikipedia/en/b/bd/Doraemon_character.png");
        account.setEncryptPassword(passwordEncoder.encode(customerDto.getEncryptPassword()));
        Set<Role> roles = new HashSet<>();
        Role customerRole = roleService.findByName(RoleName.ROLE_USER).orElse(new Role());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nhan09skbk@gmail.com");
        message.setTo(customerDto.getEmail());
        String mailSubject = "UQN Wifi Store ???? g???i m???t tin nh???n";
        String mailContent = "Ng?????i g???i: " + "UQN Wifi Store" + "\n";
        mailContent += "Sender E-mail: " + "nhan09skbk@gmail.com" + "\n";
        mailContent += "Subject: " + "Th?? ph???n h???i" + "\n";
        mailContent += "Content: " + "Ch??o m???ng qu?? kh??ch ???? ?????n v???i UQN Wifi Store" + "\n";
        mailContent += "Username: " + customerDto.getEmail() + "\n";
        mailContent += "Password: " + customerDto.getEncryptPassword() + "\n";
        mailContent += "Content: " + "Vui l??ng ????ng nh???p ????? ti???p t???c." + "\n";
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
        Optional<GetIdCustomer> idCustomer = customerService.findByEmail(signInForm.getEmail());
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrincipal.getName(),
                accountPrincipal.getAuthorities(),
                accountPrincipal.getId(),
                accountPrincipal.getEmail(),
                accountPrincipal.getAvatar(),
                idCustomer));
    }
}
