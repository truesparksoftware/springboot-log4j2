package com;

import java.util.*;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bank")
@Slf4j
public class BankController {
	
	private static final Logger LOGGER = LogManager.getLogger(BankController.class);

	@Autowired
	BankRepository bankRepository;
	
	String name="abc";


	@GetMapping("/all")
	public List<Bank> getAllBanks() {
		// System.out.println("jwt token"+jwt);
		List<Bank> bankList = bankRepository.findAll();
		LOGGER.info("application running...");
	    try {
	    	int res=20/0;
	    LOGGER.info("result..."+res);
	    }catch(Exception e) {
	    	LOGGER.error("exception occured in application..%s",e.getMessage());
	    }
		return bankList;
	}

	@GetMapping("/onebank")
	public Bank getOneBank() {
		// System.out.println("jwt token"+jwt);
		Bank b1 = new Bank(123, "INDUs", "Mum", "govt");
		log.info("get bank by id.....");
		
		return b1;
	}

	@PostMapping("/save")
	public Bank saveBankIntoDB(@RequestBody Bank bank) {
		return bankRepository.save(bank);
	}

	@PutMapping("/update")
	public Bank updateBank(@RequestBody Bank bank) {
		Optional<Bank> bankentity = bankRepository.findById(bank.getId());
		Bank bank1 = bankentity.get();

		if (!bank.getBankType().equals(bank1.getBankType())) {
			bank1.setBankType(bank.getBankType());
		}

		if (!bank.getBranch().equals(bank1.getBranch())) {
			bank1.setBranch(bank.getBranch());
		}

		if (!bank.getName().equals(bank1.getName())) {
			bank1.setName(bank.getName());
		}


		return  bankRepository.save(bank1);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBank(@PathVariable Integer id) {
		LOGGER.info("id %s ",id);
		bankRepository.deleteById(id);
		return "record deleted successfully";
	}

}
