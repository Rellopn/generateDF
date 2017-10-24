# 生成目录结构及文件的工具
**工具运行结果:** <br/>
  在`setting.yaml`中定义生成的目录结构<br/>
<img src='https://github.com/Rellopn/generateDF/blob/master/img/img3.png' height='400px' weight='450px'/><br/>
  生成的文件夹及文件:
<img src='https://github.com/Rellopn/generateDF/blob/master/img/img2.png' height='700px' weight='600px' />
<br/>>br/>
**如何使用** <br/>
1. 配置`setting.yaml`:<br/>
<img src='https://github.com/Rellopn/generateDF/blob/master/img/img1.png' height='400px' weight='450px'/><br/>
  1. 要生成的目录结构，目录层级可以无限延伸。<br/>
  2. 在哪些目录下生成1所定义的目录。注意要从根目录写起，如果是在windows下则例如：`D:\demoDir\testRestful\`。<br/>
  3. 要与2所定义的根目录数量保持一致，如果2要在三个目录下生成，则3也要定义三次。下面以每一个逗号为分隔分成1，2，3，4来介绍。<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;1. 使用的模版文件--参照模版说明。<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;2. 后缀名。<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;3. 包名。<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;4. 文件名后缀。<br/>
  4. 自定义内容，将替换模版内#{}内同名的字符 --参考模版说明
**模版说明**<br/>
模版文件在`/template`包下。
下面举一个例子🌰：
```
package #{nowPath};

import com.maryun.model.PageData;
import com.maryun.restful.base.BaseRestful;
import #{mapperPackage}.#{importPath}.#{UmapperName}Mapper;
import com.maryun.utils.WebResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Descriptkon
 *
 * @author #{author} #{date}#{secondAu}
 */
@RestController
@RequestMapping("/app/#{restName}eRestful")
public class #{UrestName}Restful extends BaseRestful{
    @Resource
    private #{UmapperName}Mapper homeThreeMapper;

    /**
     *
     * @param pd
     * @return
     **/
    @RequestMapping("")
    public PageData template(@RequestBody PageData pd) {
        return WebResult.requestSuccess();
    }
}
```
这是`testRestful`目录下的模版文件，一目了然，`#{}`及其内部的内容将会被定义在`setting.yaml`中的文件所替换。<br/>
其中有一些特殊的将在下面列举出来，在定义`setting.yaml`时不能使用相同的名字。
- `#{nowPath}`:当前包的位置
- `#{importPath}`:`setting.yaml`下
