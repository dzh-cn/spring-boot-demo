package dong.demoboot.dao;

import dong.demoboot.entity.UserLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 用户日志repository
 *
 * @author: dongzhihua
 * @time: 2018/6/22 12:24:40
 */
public interface UserLogRepository extends MongoRepository<UserLog, Long> {
}
