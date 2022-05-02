package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /*
    1. 모든 테스트 케이스를 돌리면 데이터가 저장이 되기 때문에 테스트를 하나 할때마다 데이터를 없애야 한다.
    2. 테스트는 서로 의존 관계가 없이 설계되어야 한다.
    테스트 주도 계발 (개발하기전 테스트 케이스를 먼저 작성 해보고 개발 시작)
    */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save () {

        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result);

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result =  repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }


}
