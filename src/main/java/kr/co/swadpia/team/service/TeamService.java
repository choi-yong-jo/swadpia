package kr.co.swadpia.team.service;

import kr.co.swadpia.common.constant.ResultCode;
import kr.co.swadpia.common.dto.ResponseDTO;
import kr.co.swadpia.repository.jpa.team.TeamRepository;
import kr.co.swadpia.team.dto.TeamDTO;
import kr.co.swadpia.team.entity.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>();
        teamRepository.findByUseYnOrderByTeamSeqDesc("Y").forEach(e -> teams.add((Team) e));
        return teams;
    }

    public ResponseDTO insert(TeamDTO dto) {
        ResponseDTO responseDTO = new ResponseDTO();
        Team team = new Team();
        BeanUtils.copyProperties(dto, team);
        teamRepository.save(team);
        responseDTO.setResultCode(ResultCode.INSERT.getName());
        responseDTO.setMsg(ResultCode.INSERT.getValue());
        
        return responseDTO;
    }

    public ResponseDTO update(Long teamSeq, TeamDTO dto) {
        Optional<Team> t = teamRepository.findById(teamSeq);
        ResponseDTO responseDTO = new ResponseDTO();
        if(t.isPresent()) {
            Team team = new Team();
            BeanUtils.copyProperties(dto, team);
            team.setTeamSeq(teamSeq);
            teamRepository.save(team);
            responseDTO.setResultCode(ResultCode.UPDATE.getName());
            responseDTO.setMsg(ResultCode.UPDATE.getValue());
        } else {
            responseDTO.setResultCode(ResultCode.NOT_FOUND_INFO.getName());
            responseDTO.setMsg(ResultCode.NOT_FOUND_INFO.getValue());
        }

        return responseDTO;
    }

    public ResponseDTO delete(Long teamSeq) {
        Optional<Team> t = teamRepository.findById(teamSeq);
        ResponseDTO responseDTO = new ResponseDTO();
        if(t.isPresent()) {
            Team team = t.get();
            team.setUseYn("N");
            teamRepository.save(team);
            responseDTO.setResultCode(ResultCode.DELETE.getName());
            responseDTO.setMsg(ResultCode.DELETE.getValue());
        } else {
            responseDTO.setResultCode(ResultCode.NOT_FOUND_INFO.getName());
            responseDTO.setMsg(ResultCode.NOT_FOUND_INFO.getValue());
        }

        return responseDTO;
    }
}
