package com.karyakin.PasswordGenerator;

import com.karyakin.PasswordGenerator.service.api.RandomPasswordGenerator;
import com.karyakin.PasswordGenerator.service.impl.RandomPasswordGeneratorImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RandomPasswordGeneratorApplicationTests {
	//generated tests
	@Test
	void generatePasswordSuccess() {
		RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGeneratorImpl();

		String password = randomPasswordGenerator.generate(5, 5, 3, 3);

		assert password.length() == 16;

		int lowerCaseNum = 0;
		int upperCaseNum = 0;
		int digitNum = 0;
		int specialNum = 0;

		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isLowerCase(c)) {
				lowerCaseNum++;
			} else if (Character.isUpperCase(c)) {
				upperCaseNum++;
			} else if (Character.isDigit(c)) {
				digitNum++;
			} else {
				specialNum++;
			}
		}

		assert lowerCaseNum == 5;
		assert upperCaseNum == 5;
		assert digitNum == 3;
		assert specialNum == 3;
	}

	@Test
	void generatePasswordFailNegative() {
		RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGeneratorImpl();

		try {
			String password = randomPasswordGenerator.generate(-1, 0, 0, 0);
			assert false;
		} catch (IllegalArgumentException e) {
			assert true;
		}
	}

	@Test
	void generatePasswordFailZeroLength() {
		RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGeneratorImpl();

		try {
			String password = randomPasswordGenerator.generate(0, 0, 0, 0);
			assert false;
		} catch (IllegalArgumentException e) {
			assert true;
		}
	}

}
