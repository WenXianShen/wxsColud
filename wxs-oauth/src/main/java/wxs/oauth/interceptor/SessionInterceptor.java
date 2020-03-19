package wxs.oauth.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import wxs.common.vo.user.UserResVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**自定义拦截器
 * @author : imperater
 * @date : 2019/12/6
 */
@Component
@Slf4j
public class SessionInterceptor  implements HandlerInterceptor {

    /**
     * 存放所有登录用户
     * key :userId+sessionId
     */
    public   static Map<String,String> optionMap=new HashMap<String,String> ();

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e)
            throws Exception {
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView e)
            throws Exception {
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Object) throws Exception {
        String token=request.getHeader("token");
        log.info("正在访问的url:{}",request.getRequestURL());
        log.info("token为:{}",token);

        log.info(request.getServletPath());

        //普通路径放行
        if ( "/WebLogin/login.do".equals(request.getServletPath())) {
            return true;
        }

        /**
         * 这里当前只验证是否携带token，后期还有判断token是否正确
         */
        if(token == "" || token == null){
            ((HttpServletResponse) response).setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("{\"status\":0,\"message\":\"请登录！\",\"result\":null}");
            return false;
        }
        // request.getSession().getAttribute("token");
        //权限路径拦截
        UserResVo user=(UserResVo) request.getSession().getAttribute("user");
        if(null != user) {
            String sessionId = request.getSession().getId();
            if(sessionId != null && sessionId.equals(optionMap.get(user.getId().toString()))) {  //如果该用户对应的session和当前sessionid一致，则放行
               return true;
            } else {
            //页面提示登录失效或您的账号已在其它地点登录
                System.out.println(" "+user.getId()+" 被强制挤下线,sessionId为:"+sessionId);
                ((HttpServletResponse) response).setHeader("content-type", "text/html;charset=UTF-8");
                request.getSession().removeAttribute("user");
                response.getWriter().write("{\"status\":0,\"message\":\"该账号已在别处登录！\",\"result\":null}");
                return false;
            }
        }else{
            ((HttpServletResponse) response).setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("{\"status\":0,\"message\":\"登录已失效！\",\"result\":null}");
            return false;
        }
    }


}
