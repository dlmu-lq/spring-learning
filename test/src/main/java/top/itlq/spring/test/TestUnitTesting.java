package top.itlq.spring.test;

/**
 * spring提供的不依赖容器的单元测试的支持和思路
 * mock对象：
 *      MockEnvironment MockPropertySource 模拟环境属性
 *      JDNI
 *      Servlet API 测试web context，controllers，filters等；
 *      spring web reactive
 * 单元测试支持类：
 *      ReflectionTestUtils 获得non-public域；
 *      AopTestUtils 获得代理背后的target对象；
 *      spring mvc 测试工具类；ModelAndViewAssert MockHttpServletRequest MockHttpSession等
 */
public class TestUnitTesting {
}
