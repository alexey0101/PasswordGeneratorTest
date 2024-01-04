package com.karyakin.PasswordGenerator.service.impl;

import com.karyakin.PasswordGenerator.service.api.RandomPasswordGenerator;
import org.passay.CharacterRule;
import org.passay.PasswordGenerator;
import org.passay.CharacterData;
import org.passay.EnglishCharacterData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RandomPasswordGeneratorImpl implements RandomPasswordGenerator {
    private static final CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
    private static final CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
    private static final CharacterData digitChars = EnglishCharacterData.Digit;
    private static final CharacterData specialChars = new CharacterData() {
        public String getErrorCode() {
            return "";
        }
        public String getCharacters() {
            return "!@#$%^&*()_+";
        }
    };

    @Override
    public String generate(int lowerCaseNum, int upperCaseNum, int digitNum, int specialNum) {

        if (lowerCaseNum < 0 || lowerCaseNum > 16) {
            throw new IllegalArgumentException("Lower case value must be between 0 and 16!");
        }

        if (upperCaseNum < 0 || upperCaseNum > 16) {
            throw new IllegalArgumentException("Upper case value must be between 0 and 16!!");
        }

        if (digitNum < 0 || digitNum > 16) {
            throw new IllegalArgumentException("Digit number value must be between 0 and 16!");
        }

        if (specialNum < 0 || specialNum > 16) {
            throw new IllegalArgumentException("Special numbers value must be between 0 and 16!!");
        }

        if (lowerCaseNum + upperCaseNum + digitNum + specialNum == 0) {
            throw new IllegalArgumentException("Password must contain at least 1 symbol!");
        }

        PasswordGenerator generator = new PasswordGenerator();

        List<CharacterRule> rules = new ArrayList<>();

        if (lowerCaseNum > 0) {
            CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
            lowerCaseRule.setNumberOfCharacters(lowerCaseNum);
            rules.add(lowerCaseRule);
        }

        if (upperCaseNum > 0) {
            CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
            upperCaseRule.setNumberOfCharacters(upperCaseNum);
            rules.add(upperCaseRule);
        }

        if (digitNum > 0) {
            CharacterRule digitRule = new CharacterRule(digitChars);
            digitRule.setNumberOfCharacters(digitNum);
            rules.add(digitRule);
        }

        if (specialNum > 0) {
            CharacterRule splCharRule = new CharacterRule(specialChars);
            splCharRule.setNumberOfCharacters(specialNum);
            rules.add(splCharRule);
        }

        return generator.generatePassword(lowerCaseNum + upperCaseNum
                + specialNum + digitNum, rules);
    }
}
