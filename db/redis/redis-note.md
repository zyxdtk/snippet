# redis-note
redis笔记

## 资料
- [https://redis.io/](https://redis.io/)
- [redis中文文档](http://www.redis.net.cn/) 
- [redis](https://github.com/antirez/redis) Redis源码，把readme文件看完，足够应付大部分Redis相关的问题。Redis在创建rdb备份的时候会fork一个线程，所以redis作为一个整体是存在多线程的情况的。只是对数据的操作部分是单线程，这样可以避免锁。在lru的时候也是有锁的。
  - [《Redis官方文档》使用Redis作为LRU缓存](http://ifeve.com/redis-lru/) redis采用近似LRU，而且lru策略分为两部分：选择集合+丢弃策略。丢弃策略：最近最少使用或随机，集合选择：全体、设置过TTL的数据、过期的数据。
- [Redis设计与实现](http://redisbook.readthedocs.io/en/latest/index.html)  重点可看字典和跳表的实现，可惜没有介绍Redis的LRU。关于存储：数组、链表，关于索引：hash、Btree、跳表。关于内存分配：按需、预分配。很多东西都离不开基础的数据结构。
  - [redis-3.0-annotated](https://github.com/huangz1990/redis-3.0-annotated) 黄建宏老师在写《Redis设计与实现》时对源码做的笔记，还没有看。
  - [如何高效深入的阅读Redis的源码？](https://www.zhihu.com/question/28677076) 知乎上的答案，引导我看了《Redis设计和实现》
- [go-redis](https://github.com/go-redis) 为Golang发起的Redis项目，还没看
- [redis客户端GUI](https://github.com/qishibo/AnotherRedisDesktopManager)

## 底层原理

- [redis用了哪些算法来实现他的数据结构？ - 知乎](https://www.zhihu.com/answer/2675395540) 
  - redis本身是一个大hash表。redisDB→dict→dictht→dictEntry→key(string),value(redisobject)→底层数据结构
  - redisObject: string、list、hash、set、zset, 包括type、encoding、*prt
  - 底层数据结构
    - SDS，三个字段len、alloc、flags，flags作用是控制len和alloc的大小，原因是内存对齐
    - 链表，底层listNode(prev,next,value),上面list有head、tail、len、dup、free、match。方便但是，无法很好利用 CPU 缓存导致效率低，上下游指针空间开销大。
    - 压缩列表ziplist，包含zlbytes、zltail、zllen、zlend，entry包含prevlen、encoding、data。prevlen是为了反向遍历，encoding是为了节约内存。会有连锁更新的问题。一般用在数量不多的情况。
    - 哈希表，就是dictht,包含table、size、sizemask、used，dicEntry的value是一个union 如果value是数值就不用指针了。hash冲突时用链表来处理。渐进式rehash，每次对于entry的操作都会触发迁移，知道所有迁移完。factor大于1且没有bgsave和bgrewiteaof(rdb快照或者aof重写)，factor大于5时强制rehash。
    - 整数集合, 包含encoding、len、contents，整数升级节约内存，跟压缩列表的连锁更新挺像的。
    - 跳表，包含dict、zskiplist,跳表的level是随机生成的。
      - zskiplist vs btree，zskiplist占用内存更少，缓存局部性至少与其他平衡树一样好，易于实现、调试。
    - quicklist，包含head、tail、count、len(qicklistNodes个数)，qicklistNode 包含prev、next、zl、sz、count，链表里面保存的是压缩列表。
    - listpack，对压缩列表的改进，每个entry包含encoding、data、len，每个entry只和自己有关，所以不会有连锁更新问题。

## 用法


## 问题

### [Redis 和 Memcached 各有什么优缺点，主要的应用场景是什么样的？](https://www.zhihu.com/question/19829601)
1. 支持的数据类型和操作更丰富，string、hash、list、set等
2. 内存管理机制不同。memcached采用slab，管理更有效率，不容易出现内存碎片，但是容易造成浪费。Redis采用的是包装的mallc/free，更简单。
2. Redis采用单线程模式，memcached多线程。redis的单核效率更高。
3. 支持持久化，rdb和aof
4. Redis Cluster支持分布式集群，key分到4096个槽中，每个节点可以有多个槽，每个节点会有三副本主从模式容灾。memcached需要自行开发分布式管理功能，一般采用一致性hash来做分布式管理。

### [Redis 的极限压力为啥大于 Memcache？
](https://www.zhihu.com/question/19599545)

单从代码上来看，redis不会比memcache快，memcache使用一个dispatch+n个工作进程的方式，造成了桶锁的开销，然后像redis作者说的平均到单个核上的性能redis牛逼.
1. Libevent。和Memcached不同，Redis并没有选择libevent。Libevent为了迎合通用性造成代码庞大(目前Redis代码还不到libevent的1/3)及牺牲了在特定平台的不少性能。Redis用libevent中两个文件修改实现了自己的epoll event loop(4)。业界不少开发者也建议Redis使用另外一个libevent高性能替代libev，但是作者还是坚持Redis应该小巧并去依赖的思路。一个印象深刻的细节是编译Redis之前并不需要执行./configure。
2. CAS问题。CAS是Memcached中比较方便的一种防止竞争修改资源的方法。CAS实现需要为每个cache key设置一个隐藏的cas token，cas相当value版本号，每次set会token需要递增，因此带来CPU和内存的双重开销，虽然这些开销很小，但是到单机10G+ cache以及QPS上万之后这些开销就会给双方相对带来一些细微性能差别(5)。

作者：翁伟
链接：https://www.zhihu.com/question/19599545/answer/12377607
来源：知乎
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

