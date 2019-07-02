package net.xiake6.service;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class HystrixService {
	@HystrixCommand(commandProperties = {
				// 属性参考文档：https://www.jianshu.com/p/39763a0bd9b8
				// 熔断器在整个统计时间内是否开启的阀值
				@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
				/**
				 * execution.isolation.strategy可以取如下值， THREAD（默认）
				 * 在固定大小线程池中，以单独线程执行，并发请求数受限于线程池大小。 SEMAPHORE —— 在调用线程中执行，通过信号量来限制并发量。
				 * 注：有人反馈在Junit中，使用默认的THREAD时发现事务无法回滚，见文档：https://blog.csdn.net/u012280292/article/details/80064720
				 */
				@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
				/**
				 * 设置在一个滚动窗口中，打开断路器的最少请求数，默认值为20。
				 * 比如：如果值是20，在一个窗口内（比如10秒），收到19个请求，即使这19个请求都失败了，断路器也不会打开。
				 */
				@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
				// 是否开启熔断的出错率，默认值为50，表示当出错率超过50%后熔断器启动
				@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
				// 熔断器工作时间，超过这个时间，先放一个请求进去，成功的话就关闭熔断，失败就再等一段时间，默认值为5000
				@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
				// 是否开启超时，默认值为true
				@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
				// 执行的超时时间，默认值为1000ms，超时后就会调用fallbackMethod方法，其受控于属性execution.timeout.enabled是否开启
				@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
				
				// 滑动统计的桶数量
				/**
				 * 设置滑动统计的桶数量。默认10
				 * 设置一个rolling window被划分的数量，若numBuckets＝10，metrics.rollingStats.timeInMilliseconds＝10000，
				 * 那么一个bucket的时间即1秒，必须符合metrics.rollingStats.timeInMilliseconds % numberBuckets == 0，否则会抛出异常。
				 * 默认值为10。
				 */
				@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
				// 设置滑动窗口的统计时间。熔断器使用这个时间
				/**
				 * 
				 * 设置统计的时间窗口值的，毫秒值。 circuit break 的打开会根据1个rolling window的统计来计算。 若rolling
				 * window被设为10000毫秒，则rolling window会被分成n个buckets，
				 * 每个bucket包含success，failure，timeout，rejection的次数的统计信息。默认10000
				 **/
				@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000") 
			}, 
			threadPoolProperties= {
				// 核心纺程池的大小
				@HystrixProperty(name = "coreSize", value = "30"),
				/**
				 * 此属性用于设置线程池的最大大小，这是可以支持的最大并发数，超过这个数据就会启动拒绝HystrixCommands。
				 * 请注意，此设置仅在将属性allowMaximumSizeToDivergeFromCoreSize的值设置为true时生效。
				 * 在1.5.9之前，coreSize和maxQueueSize始终相等。
				 * 注：从maven仓库拉下来的1.5.18版本中没有该属性
				 */
				//@HystrixProperty(name = "maximumSize", value = "30"),
				/**
				 * 此属性设置BlockingQueue实现的最大队列大小，默认值为-1。
				 * 如果将此值设置为-1，则将使用SynchronousQueue，否则将使用由该值指定大小的LinkedBlockingQueue。
				 * 注意：此属性仅在初始化时应用，因为如果不重新初始化线程，则无法调整大小或更改队列实现。
				 * 如果需要克服此限制，并允许对队列进行动态更改，可以使用queueSizeRejectionThreshold进行控制。
				 * 要在SynchronousQueue和LinkedBlockingQueue之间进行更改，需要重新启动。
				 */
				@HystrixProperty(name = "maxQueueSize", value = "50"),
				/**
				 * 设置队列拒绝的阈值,即使maxQueueSize还没有达到，默认值为5。
				 * 注：当maxQueueSize的值为-1时，该配置不起效果。
				 */
				@HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
				/**
				 * 设置存活时间，单位分钟。如果coreSize小于maximumSize，那么该属性控制一个线程从实用完成到被释放的时间.
				 * 我们知道，线程池内核心线程数目都在忙碌，再有新的请求到达时，线程池容量可以被扩充为到最大数量。
				 * 等到线程池空闲后，多于核心数量的线程还会被回收，此值指定了线程被回收前的存活时间，默认为 2，即两分钟。
				 */
				@HystrixProperty(name = "keepAliveTimeMinutes", value = "2")
			},
			fallbackMethod = "hystrixFallbackMethod")
	public Integer hystrixDemoMethod(Integer number) {
		if (number == -1) {
			throw new RuntimeException("-1 exception");
		} else if (number > 2000) {
			try {
				Thread.sleep(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return (int) (Math.random() * 1000);
	}

	public Integer hystrixFallbackMethod(Integer number) {
		return -1;
	}
}
