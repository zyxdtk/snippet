# 分布式管理

## 分布式锁

- [聊聊分布式锁-字节跳动技术团队-林坚](https://mp.weixin.qq.com/s/-N4x6EkxwAYDGdJhwvmZLw)
  - 单机锁 
    - 获取锁，需要 lockkey，reqestId，nx，px expireTime，避免重复申请，一直占用的问题
    - 释放锁，需要lua脚本保证查询、比对、删除的原子性。
    - watchdog，用来解决锁超时问题，就是一个定时任务不断续钟
  - 分布式环境
    - wait, 增强安全性，但还是没解决。就是需要等待指定数量的副本执行成功。
    - redlock, https://redis.io/topics/distlock, Redis 之父 antirez 设计了 Redlock 算法
      - 5个master，申请过半数的锁
      - 失败重试需要注意脑裂问题，应该尽可能同时申请申请失败应该尽快释放，然后随机退避之后重新申请
      - 释放锁。就是同时向5个master都释放锁
      - aof崩溃恢复，可能会出现锁信息丢失
        - 持久化配置中设置fsync=always，性能大大降低
        - 恰当的运维，把崩溃节点进行延迟重启，超过崩溃前所有锁的 TTL 时间之后才加入 Redlock 节点组
      - Redlock 论战：Martin Kleppmann vs. Antirez  https://martin.kleppmann.com/2016/02/08/how-to-do-distributed-locking.html

        - Martin 在这篇文章中谈及了分布式系统的很多基础性的问题（特别是分布式计算的异步模型），对分布式系统的从业者来说非常值得一读。这篇文章大体可以分为两大部分：
        - 前半部分，与 Redlock 无关。Martin 指出，即使我们拥有一个完美实现的分布式锁（带自动过期功能），在没有共享资源参与进来提供某种 fencing 机制的前提下，我们仍然不可能获得足够的安全性。
        - 后半部分，是对 Redlock 本身的批评。Martin 指出，由于 Redlock 本质上是建立在一个同步模型之上，对系统的记时假设(timing assumption)有很强的要求，因此本身的安全性是不够的。
