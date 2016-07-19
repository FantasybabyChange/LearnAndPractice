package com.fantasybaby.rcp;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

 class BookSurveyWizard extends Wizard{  
    public static final String Q1 = "QUESTION_1";  
    public static final String Q2 = "QUESTION_2";  
    public static final String THANKS = "THANKS";  
      
    private QuestionOne one;  
    private QuestionTwo two;  
    private Thanks thanks;  
    public BookSurveyWizard(){  
        one = new QuestionOne();  
        two = new QuestionTwo();  
        thanks = new Thanks();  
          
        this.addPage(one);  
        this.addPage(two);  
        this.addPage(thanks);  
        this.setWindowTitle("读者调查向导");  
    }  
      
    public boolean canFinish(){  
        if(this.getContainer().getCurrentPage() == thanks)  
            return true;  
        else  
            return false;  
    }  
      
    public boolean performFinish(){  
        return true;  
    }  
}  


 class QuestionOne extends WizardPage{  
    public QuestionOne(){  
        super(BookSurveyWizard.Q1, "问题1：", ImageDescriptor.createFromFile(QuestionOne.class, "q.gif"));  
        this.setMessage("您认为这本书的难度是:");  
    }  
      
    public void createControl(Composite parent){  
        Composite composite = new Composite(parent, SWT.NONE);  
        composite.setLayout(new GridLayout(2, false));  
        new Label(composite, SWT.LEFT).setText("A.");  
        Button b1 =  new Button(composite, SWT.RADIO);  
        b1.setText("太简单");  
        b1.setSelection(true);  
        new Label(composite, SWT.LEFT).setText("B.");  
        Button b2 = new Button(composite, SWT.RADIO);  
        b2.setText("还可以");  
        new Label(composite, SWT.LEFT).setText("C.");  
        Button b3 = new Button(composite, SWT.RADIO);  
        b3.setText("太难");  
        setControl(composite);  
    }  
}    
 class QuestionTwo extends WizardPage{  
    public QuestionTwo(){  
        super(BookSurveyWizard.Q2, "问题2", ImageDescriptor.createFromFile(QuestionOne.class, "q.gif"));  
        this.setMessage("您会考虑在今后的项目中使用SWT开发桌面程序吗:");  
    }  
      
    public void createControl(Composite parent){  
        Composite composite = new Composite(parent, SWT.NONE);  
        composite.setLayout(new GridLayout(2, false));  
        new Label(composite, SWT.LEFT).setText("A.");  
        Button b1 = new Button(composite, SWT.RADIO);  
        b1.setText("会");  
        b1.setSelection(true);  
        new Label(composite, SWT.LEFT).setText("B.");  
        Button b2 = new Button(composite, SWT.RADIO);  
        b2.setText("可能会");  
        new Label(composite, SWT.LEFT).setText("C.");  
        Button b3 = new Button(composite, SWT.RADIO);  
        b3.setText("不会");  
        setControl(composite);  
    }  
}  
 class Thanks extends WizardPage{  
    protected Thanks(){  
        super(BookSurveyWizard.THANKS, "感谢:",ImageDescriptor.createFromFile(QuestionOne.class, "q.gif"));  
        this.setMessage("非常感谢您参加本次调查！");  
    }  
      
    public void createControl(Composite parent){  
        Composite composite = new Composite(parent, SWT.NONE);  
        composite.setLayout(new FillLayout());  
        new Label(composite, SWT.CENTER).setText("感谢您的支持");  
        setControl(composite);  
    }  
}  

public class WizardTest extends ApplicationWindow{  
    public WizardTest(){  
        super(null);  
    }  
      
    protected Control createContents(Composite parent){  
        parent.setLayout(new RowLayout(SWT.VERTICAL));  
        Button button  = new Button(parent, SWT.NONE);  
        button.setText("打开简单向导对话框");  
        button.addSelectionListener(new SelectionAdapter(){  
            public void widgetSelected(SelectionEvent e){  
                //建立并打开打开向导对话框，该对话框使用了 BookSurveyWizard 向导  
                WizardDialog dlg = new WizardDialog(Display.getCurrent().getActiveShell(),  
                        new BookSurveyWizard());  
                dlg.open();  
            }  
        });  
        return parent;  
    }  
      
    public static void main(String[] args){  
        WizardTest test = new WizardTest();  
        test.setBlockOnOpen(true);  
        test.open();  
        Display.getCurrent().dispose();  
    }  
}  