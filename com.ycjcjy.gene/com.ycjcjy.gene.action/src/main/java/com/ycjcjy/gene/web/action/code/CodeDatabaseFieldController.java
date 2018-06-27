package com.ycjcjy.gene.web.action.code;


import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.CodeDatabaseField;
import com.ycjcjy.gene.service.CodeDatabaseFieldService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据库模型字段管理
 * @author 0neBean
 */
@Controller
@RequestMapping("databasefield")
public class CodeDatabaseFieldController extends TlBaseController<CodeDatabaseField,CodeDatabaseFieldService> {
}