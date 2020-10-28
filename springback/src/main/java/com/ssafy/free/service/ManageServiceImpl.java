package com.ssafy.free.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import com.ssafy.free.dto.Test;
import com.ssafy.free.dto.TestResponse;
import com.ssafy.free.repository.AdminUserRepository;
import com.ssafy.free.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private AdminUserRepository adminDao;

    @Override
    public int createTest(HashMap<String, Object> request) {
        int admin_no = adminDao.findOneByEmail(request.get("email").toString()).getAdminNo();

        // 혹시 시분초까지 필요하면 이걸 사용 LocalDateTime
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
        // HH:mm:ss.SSS");
        try {
            LocalDate start = LocalDate.parse(request.get("start").toString(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate end = LocalDate.parse(request.get("end").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            Test test = new Test(admin_no, request.get("test_title").toString(), request.get("test_a").toString(),
                    request.get("test_b").toString(), start, end, Integer.parseInt(request.get("per_a").toString()),
                    Integer.parseInt(request.get("per_b").toString()), request.get("status").toString());
            testRepository.save(test);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int modifyTest(HashMap<String, Object> request) throws Exception{
        // 테스트 번호로 기존 테스트 불러오기
        try {
            Test test = testRepository.getOne(Integer.parseInt(request.get("test_no").toString()));
            System.out.println(test.toString());
            test.setTestTitle(request.get("test_title").toString());
            test.setTestA(request.get("test_a").toString());
            test.setTestB(request.get("test_b").toString());
            
            LocalDate end = LocalDate.parse(request.get("end").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            test.setEnd(end);
            testRepository.save(test);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<TestResponse> getTestList(String email) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        List<TestResponse> testList = testRepository.findAllByAdminNo(admin_no);
        return testList;
    }

	@Override
	public List<TestResponse> getTestListBefore(String email) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        List<TestResponse> testList = testRepository.findAllByAdminNoAndStatus(admin_no, "진행전");
		return testList;
	}

    @Override
    public List<TestResponse> getTestListProgress(String email) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        List<TestResponse> testList = testRepository.findAllByAdminNoAndStatus(admin_no, "진행중");
		return testList;
    }

    @Override
    public List<TestResponse> getTestListComplete(String email) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        List<TestResponse> testList = testRepository.findAllByAdminNoAndStatus(admin_no, "진행완료");
		return testList;
    }

	@Override
	public int deleteTest(int test_no) {
        try {
            testRepository.deleteById(test_no);;
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

	}

    



}
