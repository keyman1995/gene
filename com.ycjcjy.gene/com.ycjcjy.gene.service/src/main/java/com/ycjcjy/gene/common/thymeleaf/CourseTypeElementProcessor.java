package com.ycjcjy.gene.common.thymeleaf;

import com.ycjcjy.gene.common.thymeleaf.base.OneBeanBaseAbstractElementTagProcessor;
import com.ycjcjy.gene.service.CourseTypeService;
import com.ycjcjy.gene.service.impl.CourseTypeServiceImpl;
import net.onebean.component.SpringUtil;
import net.onebean.util.StringUtils;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.*;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;


/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/14
 **/

@Component
public class CourseTypeElementProcessor extends OneBeanBaseAbstractElementTagProcessor {

    private static final String PREFIX = "tree";
    private static final String TAG_NAME = "course";
    private static final int PRECEDENCE = 1000;

    public CourseTypeElementProcessor() {
        super(TemplateMode.HTML,PREFIX,TAG_NAME,true,null,false,PRECEDENCE);
    }

    public CourseTypeElementProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML,dialectPrefix,TAG_NAME,true,null,false,PRECEDENCE);
    }

    @Override
    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag tag, IElementTagStructureHandler iElementTagStructureHandler) {
        final IModelFactory modelFactory = iTemplateContext.getModelFactory();
        final String pid = tag.getAttributeValue("pid");
        final String selfId = tag.getAttributeValue("selfId");
        final String disabled = tag.getAttributeValue("disabled");
        final String businessIputId = tag.getAttributeValue("businessInputId");

        final CourseTypeService courseTypeService = SpringUtil.getBean(CourseTypeServiceImpl.class);

        final IModel input = modelFactory.createModel();
        IOpenElementTag inputStart = modelFactory.createOpenElementTag("input");
        final ICloseElementTag inputEnd = modelFactory.createCloseElementTag("input");
        inputStart = modelFactory.setAttribute(inputStart,"id","courseTypeInput");
        inputStart = modelFactory.setAttribute(inputStart,"type","text");
        inputStart = modelFactory.setAttribute(inputStart,"class","tpl-form-input");
        inputStart = modelFactory.setAttribute(inputStart,"placeholder","请选择上级课程类型");
        inputStart = modelFactory.setAttribute(inputStart,"onclick","modalCourseTypeTree("+selfId+","+businessIputId+")");
        inputStart = modelFactory.setAttribute(inputStart,"name","courseTypeTree");
        if (pid == null || pid.equals("0")) {
            inputStart = modelFactory.setAttribute(inputStart,"value","未选择课程类型");
        } else {
            inputStart =modelFactory.setAttribute(inputStart,"value",courseTypeService.findById(pid).getName());
        }
        if (StringUtils.isNotEmpty(disabled) && disabled.equals("disabled")) {
            inputStart =modelFactory.setAttribute(inputStart,"disabled",disabled);
        }
        input.add(inputStart);
        input.add(inputEnd);
        /*模态弹窗模板*/
        Object templFragmentObj = computeFragment(iTemplateContext, "~{/public/courseTypeTree :: typeTreeTips}");
        final IModel treeTempl = modelFactory.parse(iTemplateContext.getTemplateData(),templFragmentObj.toString());
        /*机构树js内容*/
        Object jsFragmentObj = computeFragment(iTemplateContext, "~{/public/courseTypeTree :: typeTreeTipsJs}");
        final IModel js = modelFactory.parse(iTemplateContext.getTemplateData(),jsFragmentObj.toString());

        final IModel nodes = modelFactory.createModel();
        nodes.insertModel(nodes.size(),input);
        nodes.insertModel(nodes.size(),treeTempl);
        nodes.insertModel(nodes.size(),js);
        /*Instruct the engine to replace this entire element with the specified model.*/
        iElementTagStructureHandler.replaceWith(nodes, false);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    /*   @Override
    protected List<Node> getMarkupSubstitutes(Arguments arguments, Element element) {
        final ApplicationContext appCtx = ((SpringWebContext)arguments.getContext()).getApplicationContext();
        final String pid = element.getAttributeValue("pid");
        final String selfId = element.getAttributeValue("selfId");
        final String disabled = element.getAttributeValue("disabled");
        final String businessInputId = element.getAttributeValue("businessInputId");
        final Element input = new Element("input");
        final CourseTypeService courseTypeService = appCtx.getBean(CourseTypeServiceImpl.class);
        input.setAttribute("id","courseTypeInput");
        input.setAttribute("type","text");
        input.setAttribute("class","tpl-form-input treeSelector");
        input.setAttribute("placeholder","请选择上级课程类型");
        input.setAttribute("onclick","modalCourseTypeTree("+selfId+","+businessInputId+")");
        input.setAttribute("name","courseTypeTree");
        if (pid == null || pid.equals("0")) {
            input.setAttribute("value","未选择课程类型");
        } else {
            input.setAttribute("value",courseTypeService.findById(pid).getName());
        }
        if (StringUtils.isNotEmpty(disabled) && disabled.equals("disabled")) {
            input.setAttribute("disabled",disabled);
        }
*//*模态弹窗模板*//*

        final Element treeTempl = new Element("script");
        treeTempl.setAttribute("id","courseTypeTreeTempl");
        treeTempl.setAttribute("type","text/html");
        treeTempl.setAttribute("th:replace","/public/courseTypeTree :: typeTreeTips");
*//*机构树js内容*//*

        final Element js = new Element("script");
        js.setAttribute("type","text/javascript");
        js.setAttribute("th:replace","/public/courseTypeTree :: typeTreeTipsJs");
        final List<Node> nodes = new ArrayList<>();
        nodes.add(input);
        nodes.add(treeTempl);
        nodes.add(js);
        return nodes;
    }

    @Override
    public int getPrecedence() {
        return 1000;
    }*/
}
