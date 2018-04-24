package com.sitdh.analyzer.weaver;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

/**
 * Hello world!
 *
 */
public class WeaverApp 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String[] ext = new String[]{"java"};
        File tmp = new File("/Users/sitdh/Documents/workspace/weaver/src/main/java/com/sitdh/analyzer");
        
        File f = null;
        if (tmp.isDirectory()) {
        	f = tmp;
        } else {
        	f = new File(tmp.getParent());
        }
        
        Iterator<File> fs = FileUtils.iterateFiles(f, ext, true);
        while(fs.hasNext()) {
        	File fx = fs.next();
        	System.out.println(fx.getName());
        }
    }
}
