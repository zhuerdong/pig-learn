package com.pig.learn.mybatis.guava;

//简单的guava cache的使用
//        [java] view plain copy
//        1. public class GuavaCachTest {
//  2.
//          3.     private static LoadingCache<Integer,String> cache = CacheBuilder
//  4.             .newBuilder()
//  5.             .expireAfterWrite(10, TimeUnit.SECONDS)
//  6.             .maximumSize(10000)
//  7.             .build(new CacheLoader<Integer, String>(){
//        8.                 @Override
//        9.                 public String load(Integer key) throws Exception {
//            10.                     String value= key + "123";
//            11.                     System.out.println("hh");
//            12.                     return value;
//            13.                 }
//        14.             });
//  15.
//          16.     //总结其实，就是第一次缓存中没有的时候，要走load（）那段代码，否则就不会走，直接从
//          17.     //缓存里取数了
//          18.     public static void main(String[] args) throws ExecutionException {
//        19.        for(int i=0;i<=3;i++){
//            20.            System.out.println(cache.get(1));
//            21. //           hh
//            22. //           1123
//            23. //           1123
//            24. //           1123
//            25. //           1123
//            26.        }
//        27.     }
//  28. }
//
//    正常使用例子
//
//            第一步
//    public Object getIndex(int biz) throws ExecutionException {
//        return ACTIVE_MAP_LOADER.get(
//                new TargetToActivityLocalCacheKey(ActivityType.ADVERTISE, biz));
//    }
//
//第二步：
//public LoadingCache<TargetToActivityLocalCacheKey, HashMap<ActivityTargetEnum, HashSet<Integer>>> ACTIVE_MAP_LOADER = CacheBuilder.newBuilder()
//        .expireAfterWrite(10, TimeUnit.SECONDS)
//        .maximumSize(100)
//        .build(new CacheLoader<TargetToActivityLocalCacheKey, HashMap<ActivityTargetEnum, HashSet<Integer>>>() {
//@Override
//public HashMap<ActivityTargetEnum, HashSet<Integer>> load(TargetToActivityLocalCacheKey key)
//        throws Exception {
//        try {
//        return getTargetToActivityMap(key.getActivityType(), key.getBizType());
//        } catch (Exception e) {
//        ERROR_LOG.error("[ACTIVE_MAP_LOADER] error, param:{}, e=", String.valueOf(key), e);
//        return Maps.newHashMap();
//        }
//        }
//        });
//
//        第三步
//private HashMap<ActivityTargetEnum, HashSet<Integer>> getTargetToActivityMap(final ActivityType activityType,
//final Integer bizType){
//        //获得缓存中目标对象到活动的映射关系
//        HashMap<ActivityTargetEnum, HashSet<Integer>> targetToActivities = null;
//        String tairKey = TairConstants.getTargetToActivityKey(activityType, bizType);
//
//        Optional<HashMap<ActivityTargetEnum, HashSet<Integer>>> targetToActivitiesInCache =
//        noahTairClient.getOnce(tairKey);
//
//        targetToActivities = (targetToActivitiesInCache.isPresent()) ?
//        targetToActivitiesInCache.get() :
//        noahCacheService.rebuildTargetToActivitiesCache(activityType, bizType);
//
//        return targetToActivities;
//        }
//        第四步
//@Override @SuppressWarnings("unchecked")
//public HashMap<ActivityTargetEnum, HashSet<Integer>> rebuildTargetToActivitiesCache(
//final ActivityType activityType, final Integer bizType) {
//        }
//
//        详细的说明参考:http://ifeve.com/google-guava-cachesexplained/
//        http://www.cnblogs.com/parryyang/p/5777019.html
//        http://outofmemory.cn/Java/guava/cache/how-to-use-guava-cache