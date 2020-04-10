package wxs.oauth.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxs.common.util.RedisHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author : imperater
 * @date : 2020/4/2
 */
@Controller
public class TestController {


    @Autowired
    private RedisHelper redisHelper;

    /**
     * redis分布式锁防止超卖
     * @return
     */
    @RequestMapping("redisTestOrder")
    @ResponseBody
    public  String redisTestOrder(HttpServletRequest request){
        String skuId = "100";
        //判断是否还有库存，如果没有，直接返回
        int count= (int)redisHelper.get(skuId);
        if (count <= 0) {
            return "商品已卖光!";
        }
        long value = System.currentTimeMillis();
        //加锁
        while (!redisHelper.getLock("stock"+skuId,String.valueOf(value),60)) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //再次获取count数，避免用户在等待的时候，库存已经卖光
        count = (int)redisHelper.get(skuId);
        if (count <= 0) {
            if(value == (long)redisHelper.get("stock"+skuId)){
                redisHelper.del("stock"+skuId);
            }
            return "商品已卖光!";
        }
        //加锁成功之后才可以减库存
        redisHelper.decr(skuId,1);
        //解锁(使用lua脚本，保证原子性)
        redisHelper.unLock("stock"+skuId,String.valueOf(value));

        return "成功购买商品!";
    }


    /**
     * synchronize防止超卖
     */
    @RequestMapping("synchronizeTestOrder")
    @ResponseBody
    public  String synchronizeTestOrder(HttpServletRequest request){
        String skuId = "100";
        synchronized (this){
            int count= (int)redisHelper.get(skuId);
            if (count <= 0) {
                return "商品已卖光!";
            }
            //加锁成功之后才可以减库存
            redisHelper.decr(skuId,1);
        }
        return "成功购买商品!";
    }
}
