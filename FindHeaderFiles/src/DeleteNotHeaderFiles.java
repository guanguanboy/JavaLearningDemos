import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteNotHeaderFiles {
    public static void main(String[] args) {

        File dir=new File("F:\\tensorflow-v1.6.0-rc0");
        //深度遍历的目录
        //filter:过滤器
        //list:容器,存放符合条件的file对象
        FilenameFilter filter=new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return name.endsWith(".h");
            }
        };
        List<File> list=new ArrayList<File>();
        getFiles(dir,filter,list);

        File destFile=new File(dir,"javalist.txt");
        //从list中获取文对象，放到文件中

        write2File(list,destFile);

    }

    private static void write2File(List<File> list, File destFile) {
        BufferedWriter bw=null;
        try {
            bw=new BufferedWriter(new FileWriter(destFile));
            for(File file:list){
                bw.write(file.getAbsolutePath());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            //释放资源的代码
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


    }

    private static void getFiles(File dir, FilenameFilter filter, List<File> list) {

        File[] files=dir.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                //如果是目录，则递归
                getFiles(file, filter, list);
            }else{
                //文件
                //过滤文件：将符合条件的file对象存储到list集合中
                if(false == filter.accept(dir, file.getName())){
                    file.delete();
                }
            }
        }
    }

}
