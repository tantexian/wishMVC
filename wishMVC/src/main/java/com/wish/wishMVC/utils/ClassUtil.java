package com.wish.wishMVC.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;

/**
 * @Description: TODO
 * @author ttx
 * @since 2015年12月21日 下午5:04:40
 */
public class ClassUtil {
	
	/**
	 * @Description: TODO根据父类名称获取所有子类
	 * @param superClass
	 * @return
	 * @author ttx
	 * @since  2015年12月28日 上午10:30:48
	 */
	public static Set<Class<?>> getAllChildClassBySuper(Class<?> superClass){
		HashSet<Class<?>> allChildClassSet = new HashSet<Class<?>>();
		String basePackage = "com.wish.wishMVC";
		Set<Class<?>> classSet = getClassSetByPackageName(basePackage, true);
		for(Class<?> cls :  classSet){
			if(superClass.isAssignableFrom(cls) && !cls.equals(superClass)){
				allChildClassSet.add(cls);
			}
		}
		return allChildClassSet;
	}
	
	/**
	 * @Description: TODO
	 * @param interfaceClass
	 * @return
	 * @author ttx
	 * @since  2015年12月28日 上午12:10:06
	 */
	public static Set<Class<?>> getAllImplClassByInterface(Class<?> interfaceClass){
		HashSet<Class<?>> allImplClassSet = new HashSet<Class<?>>();
		if(!interfaceClass.isInterface()){
			return allImplClassSet;
		}
		String basePackage = "com.wish.wishMVC";
		Set<Class<?>> classSet = getClassSetByPackageName(basePackage, true);
		for(Class<?> cls :  classSet){
			if(interfaceClass.isAssignableFrom(cls) && !interfaceClass.equals(cls)){
				allImplClassSet.add(cls);
			}
		}
		return allImplClassSet;
	}
	
	/**
	 * @Description: TODO
	 * @param packageName
	 * @param isRecursive
	 * @return
	 * @author ttx
	 * @since  2015年12月28日 上午12:10:09
	 */
	public static Set<Class<?>> getClassSetByPackageName(String packageName, boolean isRecursive){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		try {
			//正则表达式：java将"\\."转义表示为"\.",正则表达式规则再将"\."表示为".",因此需要两个斜杠来转义
			Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				if(url != null){
					String protocol = url.getProtocol();
					if("file".equals(protocol)){
						String absolutePath = url.getPath();
						addClass2Set(classSet, absolutePath, packageName, isRecursive);
					}else if("jar".equals(protocol)){
						new Exception("jar has not implment!!!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classSet;
	}
	
	/**
	 * @Description: TODO
	 * @param classSet
	 * @param absolutePath
	 * @param packageName
	 * @param isRecursive
	 * @author ttx
	 * @since  2015年12月28日 上午12:10:13
	 */
	private static void addClass2Set(Set<Class<?>> classSet, String absolutePath, String packageName, final boolean isRecursive) {
		File[] files = new File(absolutePath).listFiles(new FileFilter(){

			public boolean accept(File file) {
				//如果文件为class或者递归参数为true则递归查找目录（这样对于包下面不是class的文件则不会处理）
				return (file.isFile() && file.getName().endsWith(".class")
						|| isRecursive && file.isDirectory());
			}
			
		});
		for(File file : files){
			//如果为class文件，则直接获取类加入到classSet
			if(file.isFile()){
				String justFileName = file.getName().substring(0, file.getName().lastIndexOf("."));
				String packageAndClassName = packageName + "." + justFileName;
				try {
					//将 initialize 设定为 false，这样在加载类时并不会立即运行静态区块，而会在使用类建立对象时才运行静态区块。
					Class<?> clazz = Class.forName(packageAndClassName, false, Thread.currentThread().getContextClassLoader());
					classSet.add(clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}else if(isRecursive){//如果为目录且isRecursive =true，则需要递归添加子目录下面的class文件
				String childDir = file.getName();
				String childPackageName = packageName + "." + childDir;
				String childDirAbsolutePath = file.getAbsolutePath();
				//递归查找子包下面的所有class文件
				addClass2Set(classSet, childDirAbsolutePath, childPackageName, isRecursive);
			}
		}
	}

	/**
	 * @Description: TODO
	 * @param annotationClass
	 * @return
	 * @author ttx
	 * @since  2015年12月28日 上午12:23:32
	 */
	public static Set<Class<?>> getClassSetWithAnnotationClass(Class<? extends Annotation> annotationClass){
		Set<Class<?>> annotationedClassSet = new HashSet<Class<?>>();
		Set<Class<?>> allClassSet = getClassSetByPackageName("com.wish.wishMVC", true);
		for(Class<?> cls : allClassSet){
			if(cls.isAnnotationPresent(annotationClass)){
				annotationedClassSet.add(cls);
			}
		}
		return annotationedClassSet;
	}
	

	/**
	 * @Description: TODO
	 * @param packageName
	 * @param annotationClass
	 * @return
	 * @author ttx
	 * @since  2015年12月28日 上午12:03:35
	 */
	public static List<Class<?>> getClassListByAnnotation(String packageName,
			Class<? extends Annotation> annotationClass) {
		List<Class<?>> classList = new ArrayList<Class<?>>();
		Enumeration<URL> urls;
		try {
			urls = Thread.currentThread().getContextClassLoader()
					.getResources(packageName.replaceAll("\\.", "/"));
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String protocol = url.getProtocol();
					if ("file".equals(protocol)) {
						String packagePath = url.getPath();
						addClassByAnootation(classList, packagePath, packageName, annotationClass);
					} else if ("jar".equals(protocol)) {
						//暂且不处理jar包
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classList;
	}

	private static void addClassByAnootation(List<Class<?>> classList,
			String packagePath, String packageName,
			Class<? extends Annotation> annotationClass) {
		// 根据packagePath获取该路径下面所有目录及文件
		File[] fileList = new File(packagePath).listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return ((file.isDirectory()) || (file.isFile() && file
						.getName().endsWith(".class")));
			}
		});
		if (fileList != null) {
			for (File file : fileList) {
				if (file.isFile()) {
					String fileName = file.getName();
					String justFileName = fileName.substring(0,
							fileName.lastIndexOf("."));
					String packageAndClassName = packageName + justFileName;
					Class<?> cls;
					try {
						cls = Class.forName(packageAndClassName);
						if (cls.isAnnotationPresent(annotationClass)) {
							classList.add(cls);
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else {
					// 其中packagePath为当前file的绝对路径。
					// packageName则为packageName+当前文件名称（即目录）
					addClassByAnootation(classList, file.getAbsolutePath(),
							packageName + file.getName(), annotationClass);
				}

			}
		}

	}

	
	public static List<Class<?>> getClassList(String packagename, boolean isRecursive) {
		List<Class<?>> classList = new ArrayList<Class<?>>();
		try {
			Enumeration<URL> urls = Thread.currentThread()
					.getContextClassLoader()
					.getResources(packagename.replaceAll("\\.", "/"));
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String protocol = url.getProtocol();
					if ("file".equals(protocol)) {
						String packagePath = url.getPath();
						addClass2List(classList, packagePath, packagename, isRecursive);
					} else if ("jar".equals(protocol)) {
						JarURLConnection jarConnection = (JarURLConnection) url.openConnection();
						Enumeration<JarEntry> jarEntrys = jarConnection.getJarFile().entries();
						while (jarEntrys.hasMoreElements()) {
							JarEntry jarEntry = jarEntrys.nextElement();
							String jarName = jarEntry.getName();
							if (jarName.endsWith(".class")) {
								// Class<? extends JarEntry> className =
								// jarEntry.getClass();
								String className = jarName.substring(0, jarName.lastIndexOf(".")).replace("/", ".");
								if (isRecursive || className.substring(0, className.lastIndexOf(".")).equals(packagename)) {
									try {
										classList.add(Class.forName(className));
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}

					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return classList;
	}
	

	/**
	 * @Description: 根据包路径及包名获取该路径下所有class
	 *               首先把packagePath下面的所有class文件获取（如果isRecursive递归为真
	 *               ，则还需要将所有子目录获取）
	 * @param classList
	 *            保存class的链表
	 * @param packagePath
	 *            包路径 （例如：C:\Users\ttx\workspace-wishMVC\wishMVC\src\main\java）
	 * @param packageName
	 *            包名 （例如：com.wish.wishMVC）
	 * @param isRecursive
	 *            是否递归查找（表示如果packagePath(com.wish.wishMVC)还有子目录(com.wish.wishMVC.controller)需要继续获取该子目录下面的class）
	 * @author ttx
	 * @since 2015年12月21日 下午5:02:47
	 */
	private static void addClass2List(List<Class<?>> classList, String packagePath, String packageName, final boolean isRecursive) {
		// 根据packagePath获取该路径下面所有目录及文件
		File[] fileList = new File(packagePath).listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return ((isRecursive && file.isDirectory()) 
						|| (file.isFile() && file.getName().endsWith(".class")));
			}
		});
		if (fileList != null) {
			for (File file : fileList) {
				String fileName = file.getName();
				if (file.isFile()) {
					String justFileName = fileName.substring(0,
							fileName.lastIndexOf("."));
					String packageAndClassName = packageName + "." + justFileName;
					try {
						classList.add(Class.forName(packageAndClassName));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					if (isRecursive) {
						// 如果递归参数isRecursive为true，则递归调用addClass2List
						// 其中packagePath为当前file的绝对路径。
						// packageName则为packageName+当前文件名称（即目录）
						addClass2List(classList, file.getAbsolutePath(),
								packageName + "." + file.getName(), isRecursive);
					}
				}
			}
		}
	}
}
