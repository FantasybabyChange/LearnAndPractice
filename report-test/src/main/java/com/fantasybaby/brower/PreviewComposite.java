package com.fantasybaby.brower;

/*
 *  Examples on using SWT Controls.
 *
 * Created on Mar 28, 2005
 *
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Basic SWT Controls example. Shows use of basic input controls like Text and
 * Buttons. Shows basic LayoutManager use.
 *
 * @author barryf
 */
public class PreviewComposite extends Composite {


	protected Composite printPanel;


	/**
	 * Constructor.
	 */
	public PreviewComposite(Composite parent) {
		this(parent, SWT.NONE);
	}

	/**
	 * Constructor.
	 */
	public PreviewComposite(Composite parent, int style) {
		super(parent, style); // must always supply parent and style
		createGui();
		this.createPrintPanel(parent);
	}


	/**
	 * 初始化打印面板-在UI上不显示，仅做父节点用
	 */
	protected void createPrintPanel(Composite parent) {
		if (printPanel == null || printPanel.isDisposed()) {
			printPanel = new Composite(parent, SWT.NONE);
//			FormData layoutData = new FormData();
//			layoutData.width = 100;
//			layoutData.height = 0;
//			printPanel.setLayoutData(layoutData);
			printPanel.setLayout(new GridLayout(1, true));
			printPanel.setVisible(false);
		}
	}

	protected void createGui() {
		// this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		this.setLayout(new FillLayout());
		this.setBounds(0, 0, 480, 480);

		// this.setBackgroundImage(image);

	}


	/**
	 * 创建Label对象
	 *
	 * @param parent
	 * @param item
	 */

	/**
	 * Main driver program.
	 */
//	public static void main(String[] args) {
//		// the display allows access to the host display device
//		final Display display = new Display();
//
//		// the shell is the top level window (AKA frame)
//		final Shell shell = new Shell(display);
//		shell.setText("打印模板设置"); // Give me a title
//		shell.setBackgroundMode(SWT.INHERIT_DEFAULT); // 设置父控件的背景模式，即所有的子控件采用父控件的
//
//		// all children split space equally
//		// shell.setLayout(null);
//		shell.setSize(1200, 700);
//		shell.setLayout(new FillLayout());
//
//		BillConfig config = ConfigManager.getInstance().loadBillConfig(BillConfig.class,
//				"/express/express-template.xml");
//		final int WIDTH = 900;
//		final int HEIGHT = WIDTH * config.getHeight() / config.getWidth();
//		config.setWidth(WIDTH);
//		config.setHeight(HEIGHT);
//
//		Composite composite = new Composite(shell, SWT.NONE);
//		composite.setLayout(new GridLayout(2, false));
//
//		Composite previewContainer = new Composite(composite, SWT.BORDER);
//		GridData previewGridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
//		previewContainer.setLayoutData(previewGridData);
//		GridLayout layout = new GridLayout(1, true);
//		layout.marginWidth = 0;
//		layout.marginHeight = 0;
//		previewContainer.setLayout(layout);
//
//		Composite innerContainer = new Composite(previewContainer, SWT.BORDER);
//		innerContainer.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
//		innerContainer.setLayout(null);
//
//		// 预览面板
//		PreviewComposite previewComposite = new PreviewComposite(innerContainer, config);
//		// 属性面板
//		PropertyComposite propertyComposite = new PropertyComposite(composite, config);
//		GridData propertyGridData = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
//		propertyGridData.widthHint = 250;
//		propertyComposite.setLayoutData(propertyGridData);
//
//		// 建立关联
//		propertyComposite.setPreviewComposite(previewComposite);
//		previewComposite.setPropertyComposite(propertyComposite);
//
//		// shell.pack(); // make shell take minimum size
//		shell.open(); // open shell for user access
//
//		// process all user input events until the shell is disposed (i.e.,
//		// closed)
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch()) { // process next message
//				display.sleep(); // wait for next message
//			}
//		}
//		display.dispose(); // must always clean up
//	}
}