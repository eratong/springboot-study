package utilTest;

import com.ontg.demo.Utils.FilesRenameUtil;
import org.junit.Test;

public class FilesRenameTest {

    @Test
    public void s() {
        String path = "C:\\Users\\EDZ\\Desktop\\新建文件夹\\ms016000110";

        FilesRenameUtil.fileRename(path, path.substring(path.indexOf("m"), path.length()));
    }


    @Test
    public void s2() {
        String path = "C:\\Users\\EDZ\\Desktop\\dd\\ms013000101";

        FilesRenameUtil.fileRename(path, path.substring(path.indexOf("m"), path.length()));
    }
}
