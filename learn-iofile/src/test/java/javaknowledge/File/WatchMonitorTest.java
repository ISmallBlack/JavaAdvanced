package javaknowledge.File;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileMode;
import cn.hutool.core.io.file.Tailer;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.io.watch.watchers.DelayWatcher;
import cn.hutool.core.io.watch.watchers.WatcherChain;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * 文件监听单元测试
 * 
 * @author Looly
 *
 */
public class WatchMonitorTest {

	public static void main(String[] args) {
		Watcher watcher = new SimpleWatcher(){
			@Override
			public void onCreate(WatchEvent<?> event, Path currentPath) {
				Object obj = event.context();
				Console.log("创建：{}-> {}", currentPath, obj);
			}

			@Override
			public void onModify(WatchEvent<?> event, Path currentPath) {
				Object obj = event.context();
				Console.log("修改：{}-> {}", currentPath, obj);
			}

			@Override
			public void onDelete(WatchEvent<?> event, Path currentPath) {
				Object obj = event.context();
				Console.log("删除：{}-> {}", currentPath, obj);
			}

			@Override
			public void onOverflow(WatchEvent<?> event, Path currentPath) {
				Object obj = event.context();
				Console.log("Overflow：{}-> {}", currentPath, obj);
			}
		};
		File file = FileUtil.file("E:\\FileTest\\sss.txt");
		RandomAccessFile randomAccessFile = FileUtil.createRandomAccessFile(file, FileMode.r);
		LineReadWatcher lineReadWatcher = new LineReadWatcher(randomAccessFile, CharsetUtil.CHARSET_UTF_8, Tailer.CONSOLE_HANDLER);
		WatcherChain watcherChain = new WatcherChain();
		watcherChain.addChain(watcher);
		watcherChain.addChain(lineReadWatcher);
		WatchMonitor monitor = WatchMonitor.createAll(file, new DelayWatcher(watcherChain, 500));
		monitor.setMaxDepth(0);
		monitor.start();
	}
	
	
}
