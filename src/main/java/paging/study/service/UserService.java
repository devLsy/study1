package paging.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paging.study.domain.Criteria;
import paging.study.domain.enu.UserLevelStatus;
import paging.study.domain.vo.UserVO;
import paging.study.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void joinUser(UserVO userVO) {
        // 비밀번호 암호화
        userVO.setUserPassword(passwordEncoder.encode(userVO.getUserPassword()));
        userMapper.createMember(userVO);
        Long userSeq = userVO.getUserSeq();
        log.info("=======================================");
        log.info("userSeq : " + userSeq);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserVO findMember = userMapper.findMemberInfo(userId);

        if (findMember == null) {
            return null;
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (UserLevelStatus.SUPER_ADMIN.getValue().equals(findMember.getUserLevel())) {
            authorities.add(new SimpleGrantedAuthority("SUPER_ADMIN"));
        } else if (UserLevelStatus.ADMIN.getValue().equals(findMember.getUserLevel())) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("MEMBER"));
        }
        log.info("authorities :::::::::::::: " + authorities);
        return new User(findMember.getUserId(), findMember.getUserPassword(), authorities);
    }

    /**
     * 전체 회원 목록
     * @return
     */
    public List<UserVO> findAllMembersPaging(Criteria cri) {
        return userMapper.findAllMembersPaging(cri);
    }

    /**
     * 전체 회원 카운트
     * @return
     */
    public int findCountAllUsers() {
        return userMapper.findCountAllUsers();
    }

    /**
     * 사용 회원 목록
     * @return
     */
    public List<UserVO> findUseMembersPaging(Criteria cri) {
        return userMapper.findUseMembersPaging(cri);
    }

    /**
     * 사용 회원 카운트
     * @return
     */
    public int findCountUseMember() {
        return userMapper.findCountUseMember();
    }

    /**
     * 수정
     * @param userSeq
     * @param userVO
     */
    public void modifyMember(Long userSeq, UserVO userVO) {
        userMapper.modifyMember(userSeq, userVO);
    }
}

