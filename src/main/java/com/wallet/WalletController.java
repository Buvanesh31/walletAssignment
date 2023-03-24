package com.wallet;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController

@CrossOrigin(value ="http://localhost:4200/")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletJpaRepository userRepository;

    @GetMapping("/")
    public String greet(){
        return "Hello welcome to wallet app.";
    }

    @PostMapping("wallet")
    public WalletDto registerWallet(@RequestBody WalletDto wallet) throws WalletException {
        return this.walletService.registerWallet(wallet);
    }

    @GetMapping("wallet/{id}")
    public WalletDto getWalletById(@PathVariable("id") Integer walletId) throws WalletException {
        return this.walletService.getWalletById(walletId);
    }
    @PutMapping("wallet")
    public WalletDto updateWallet(@RequestBody WalletDto wallet) throws WalletException {
        return this.walletService.updateWallet(wallet);
    }
    @DeleteMapping("wallet/{id}")
    public WalletDto deleteWallet(@PathVariable("id") Integer walletId) throws WalletException {
        return this.walletService.deleteWalletById(walletId);
    }

    @PatchMapping("wallet/{id}/{amount}")
    public Double addFundsToWalletById(@PathVariable("id")Integer walletId,@PathVariable("amount") Double amount) throws WalletException {
        return this.walletService.addFundsToWalletById(walletId,amount);
    }
    @PatchMapping("wallet/{id}/fund/{amount}")
    public Double withdrawFundsfromWalletById(@PathVariable("id")Integer walletId,@PathVariable("amount") Double amount) throws WalletException {
        return this.walletService.withdrawFundsFromWalletById(walletId,amount);
    }

    @PatchMapping("wallet/{fromid}/transfer/{toid}/{amount}")
    public Boolean fundTransfer(@PathVariable("fromid")Integer fromid,@PathVariable("toid")Integer toid,@PathVariable("amount")Double amount ) throws WalletException {
       return this.walletService.fundTransfer(fromid,toid,amount);
    }
    @GetMapping("wallets")
    public Collection<WalletDto> getAllWallets(){
        return this.walletService.getAllWallets();
    }

    //Authentication and authorization functions
    @PostMapping("login")
    public WalletDto login(@RequestBody LoginDto loginDto, HttpServletResponse response) throws Exception {

        // Create a user service and log in method
        WalletDto user=this.userRepository.findByName(loginDto.getName());
        if(user == null) throw new Exception("User does not exists");
        if(! user.getPassword().equals(loginDto.getPassword()))
            throw new Exception("User password does not match");

        // JWT util
        String issuer = loginDto.getName();
        Date expiry= new Date(System.currentTimeMillis() + (1000 * 60 * 60 ));
        String jwt = Jwts.builder().setIssuer(issuer).setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256,"secretKey").compact();

        Cookie cookie = new Cookie("jwt",jwt);
        user.setJwt(jwt);
        response.addCookie(cookie);
        //return jwt;
        return user;
    }
}
