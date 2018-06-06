/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.util
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-6
 * Time : 下午5:31
 * ---------------------------------
 */
package org.wukm.util;

import com.google.common.collect.Sets;
import com.google.common.reflect.ClassPath;
import org.codehaus.groovy.tools.shell.util.PackageHelper;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Set;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.util
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-6
 * Time : 下午5:31
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class Commons {


    public static Set<ClassPath.ClassInfo> classInfo(String packageName) {
        Set<ClassPath.ClassInfo> info = Sets.newHashSet();
        try {
            ClassPath.from(Commons.class.getClassLoader())
                    .getTopLevelClasses(packageName)
                    .forEach((ClassPath.ClassInfo classInfo) -> {
                        System.out.println("a:" + classInfo);
                        info.add(classInfo);
                    });
        } catch (IOException ignore){
            //ignore
        }
        return info;
    }

    public static Set<String> className(String packageName) {
        Set<String> name = Sets.newHashSet();
        classInfo(packageName).forEach((ClassPath.ClassInfo classInfo) -> {
            name.add(classInfo.getName());
        });
        return name;
    }

    public static Collection<String> packageName(String packageName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        return PackageHelper.getPackageNames(url);
    }
}
