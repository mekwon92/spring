package com.me92100984.jdbc.service;

import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.jdbc.dao.MemberDao;
import com.me92100984.jdbc.dao.PostDao;
import com.me92100984.jdbc.dao.ReplyDao;

import jakarta.annotation.sql.DataSourceDefinition;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberService {
  private MemberDao memberDao;
  private PostDao postDao;
  private ReplyDao replyDao;
  // private DataSourceTransactionManager manager;
  // private DataSourceDefinition definition;

  // public void quitMember(String id) {
  //   TransactionStatus status = manager.getTransaction(null);
    
  //   try {
  //     replyDao.updateToWriterNull(id);
  //     postDao.updateToWriterNull(id);
  //     memberDao.remove(id);
  //     manager.commit(status);
  //   } 
  //   catch(DataAccessException e) {
  //     manager.rollback(status);
  //   }
  // }

  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT) //주석처리된거 편하게(괄호 안은 기본값임)
  public void quitMember(String id) {
      replyDao.updateToWriterNull(id);
      postDao.updateToWriterNull(id);
      memberDao.remove(id);
  
  }
}
