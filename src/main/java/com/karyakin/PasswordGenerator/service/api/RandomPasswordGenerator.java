package com.karyakin.PasswordGenerator.service.api;

public interface RandomPasswordGenerator {
    public String generate(int lowerCaseNum, int upperCaseNum, int digitNum, int specialNum);
}
