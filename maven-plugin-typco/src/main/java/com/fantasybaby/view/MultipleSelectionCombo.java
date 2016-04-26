package com.fantasybaby.view;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
class MultipleSelectionCombo extends Composite { 
	
	 private Text _text=null; 
	 private Shell _floatShell = null; 
	 private ScrolledComposite scrollPanel;
	 private Composite checkBoxComposite;
	 private MonitorDiagramView parentView;
	 public MultipleSelectionCombo(Composite parent, 
					 int style,MonitorDiagramView parentView){ 
		 super(parent, style); 
		 this.parentView = parentView;
		 init(); 
	 } 
		 private void init(){ 
			 GridLayout layout = new GridLayout(); 
			 layout.marginBottom =0; 
			 layout.marginTop = 0; 
			 layout.marginLeft =0; 
			 layout.marginRight = 0; 
			 layout.marginWidth = 0; 
			 layout.marginHeight = 0; 
			 setLayout(layout); 
			 _text = new Text(this, SWT.BORDER | SWT.READ_ONLY); 
			 _text.setLayoutData(new GridData(GridData.FILL_BOTH)); 
			 _text.setText("监控图列表");
			 _text.addMouseListener(new MouseAdapter() { 
				  public void mouseDown(MouseEvent event) { 
					  super.mouseDown(event);
					  if(_floatShell != null){
						  _floatShell.dispose();
					  }
					  initFloatShell(); 
				  } 
			  }); 
			 initFloatShell();
			 } 
			 private void initFloatShell(){ 
				  Point p = _text.getParent().toDisplay(_text.getLocation()); 
				  Point size = _text.getSize(); 
				  Rectangle shellRect = new Rectangle(p.x, p.y + size.y, size.x, 0); 
				  _floatShell = new Shell(MultipleSelectionCombo.this.getShell(), SWT.NO_TRIM); 
				  _floatShell.setSize(shellRect.width, 100); 
				  _floatShell.setLocation(shellRect.x, shellRect.y);
				  _floatShell.setLayout(new FillLayout());
				  _floatShell.addShellListener(new ShellAdapter() { 
					 public void shellDeactivated(ShellEvent arg0) { 
						 if (_floatShell != null && !_floatShell.isDisposed()) { 
								 _floatShell.dispose(); 
							 } 
						 } 
				  }); 
				  scrollPanel = new ScrolledComposite(_floatShell, SWT.BORDER| SWT.V_SCROLL | SWT.H_SCROLL);
				 initList();
				_floatShell.open();
				scrollPanel.layout();
				checkBoxComposite.layout();
			 } 
		 private synchronized void initList(){
			  if(checkBoxComposite != null && !checkBoxComposite.isDisposed()){
				  checkBoxComposite.dispose();
			  }
			  checkBoxComposite = new Composite(scrollPanel, SWT.NONE);
			  checkBoxComposite.setLayout(new GridLayout(1,true));
			 Map<String, JFreeChartComposite> allDiagramMap = ChartDiagramConstant.allDiagramMap;
			 Set<String> keySet = allDiagramMap.keySet();
			 Iterator<String> iterator = keySet.iterator();
			 while (iterator.hasNext()) {
				 String next = iterator.next();
				 JFreeChartComposite jFreeChartComposite = allDiagramMap.get(next);
				 Button button = new Button(checkBoxComposite, SWT.CHECK);
				 button.setText(jFreeChartComposite.compositeTitle);
				 button.setData(next);
				 if(jFreeChartComposite.isEnable){
					 button.setSelection(true);
				 }else{
					 button.setSelection(false);
				 }
				 button.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							Object source = e.getSource();
							if(source != null){
								Button currentButton = (Button)source;
								Object keyObject = currentButton.getData();
								if(keyObject != null){
									if(keyObject != null){
										String key = (String)keyObject;
										JFreeChartComposite selectedComposite = ChartDiagramConstant.allDiagramMap.get(key);
										if(!selectedComposite.isEnable){
											DefaultDiagramJfreeChartAbstract selectedCompositeImp = ChartDiagramConstant.allDiagramImpMap.get(key);
											if(selectedCompositeImp != null){
												selectedCompositeImp.renderChartByDataSet(true);
											}
										}else{
											selectedComposite.dispose();
											selectedComposite.setIsEnable(false);
											TimerTask timeTask = selectedComposite.getTimeTask();
											if(timeTask != null){
												timeTask.cancel();
											}
										}
										//TODO
//										parentView.resizeParentPanel();
									}
								}
							}
						};
					}
				);
			 }
			 scrollPanel.setContent(checkBoxComposite);
			 scrollPanel.setExpandHorizontal(true);  
			 scrollPanel.setExpandVertical(true);
			 scrollPanel.setMinWidth(500);
			 scrollPanel.setMinHeight(checkBoxComposite.getChildren().length * 25);
		 }
		 
		 /*private void renderChareBySelection(){
				 try {
					 if(jFreeChartComposite.isEnable == false){
						 DefaultDiagramJfreeChartAbstract implChart = ChartDiagramConstant.allDiagramImpMap.get(chartKeys.get(intAr[i]));
						 implChart.renderChartByDataSet(true);
					 }
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		 }*/
		 public synchronized void resetSelect(){
			 initList();
		 }
	 }