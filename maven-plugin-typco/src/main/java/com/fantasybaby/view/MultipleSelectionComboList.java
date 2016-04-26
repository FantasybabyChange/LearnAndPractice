/*package com.fantasybaby.view;
class MultipleSelectionComboList extends Composite { 
	
	 Text _text=null; 
	 List<String> _items; 
	 List<JFreeChartComposite> composite ;
	 List<String> chartKeys;
	 List<Integer> _selection; 
	 Shell _floatShell = null; 
	 org.eclipse.swt.widgets.List _list = null; 
	 int[] intAr;
	 public MultipleSelectionComboList(Composite parent, 
					 int style){ 
		 super(parent, style); 
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
			 setLayout(new GridLayout()); 
			 _text = new Text(this, SWT.BORDER | SWT.READ_ONLY); 
			 _text.setLayoutData(new GridData(GridData.FILL_BOTH)); 
			 _text.setText("监控图列表");
			 _text.addMouseListener(new MouseAdapter() { 
				  public void mouseDown(MouseEvent event) { 
					  super.mouseDown(event);
					  if(_floatShell != null){
						  _floatShell.dispose();
					  }
					  resetData();
					  initFloatShell(); 
					  displayText();
				  } 
			  }); 
			 } 
			 private void initFloatShell(){ 
			  Point p = _text.getParent().toDisplay(_text.getLocation()); 
			  Point size = _text.getSize(); 
			  Rectangle shellRect = new Rectangle(p.x, p.y + size.y, size.x, 0); 
			  _floatShell = new Shell(MultipleSelectionCombo.this.getShell(), SWT.NO_TRIM); 
			  GridLayout gl = new GridLayout(); 
			  gl.marginBottom = 2; 
			  gl.marginTop = 2; 
			  gl.marginRight = 2; 
			  gl.marginLeft = 2; 
			  gl.marginWidth = 0; 
			  gl.marginHeight = 0; 
			  _floatShell.setLayout(gl); 

			  _list = new org.eclipse.swt.widgets.List(_floatShell, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL); 
			  initList();
				
			  _floatShell.setSize(shellRect.width, 100); 
			  _floatShell.setLocation(shellRect.x, shellRect.y); 
			  _floatShell.addShellListener(new ShellAdapter() { 
				 public void shellDeactivated(ShellEvent arg0) { 
					 if (_floatShell != null && !_floatShell.isDisposed()) { 
						 intAr = _list.getSelectionIndices(); 
						 displayText(); 
						 _floatShell.dispose(); 
					 } 
				 } 
			 }); 
			_floatShell.open(); 
		 } 
		 private void displayText(){ 
			   if(_selection!=null && intAr.length>0){
				 StringBuffer sb = new StringBuffer(); 
				 for(int i=0;i<_selection.size();i++){ 
					 if(i>0)sb.append(","); 
					 sb.append(_items.get(intAr[i])); 
				 } 
				 _text.setText(sb.toString()); 
			 }else{ 
				 _text.setText("监控图"); 
			 } 
		 }
		 public void resetData(){
			 Map<String, JFreeChartComposite> allDiagramMap = ChartDiagramConstant.allDiagramMap;
			 Set<String> keySet = allDiagramMap.keySet();
			 Iterator<String> iterator = keySet.iterator();
			 _items = new ArrayList<String>();
			 _selection = new ArrayList<Integer>();
			 composite  = new ArrayList<JFreeChartComposite>();
			 chartKeys = new ArrayList<String>();
			 while (iterator.hasNext()) {
				 String next = iterator.next();
				 chartKeys.add(next);
				 JFreeChartComposite jFreeChartComposite = allDiagramMap.get(next);
				 _items.add(jFreeChartComposite.compositeTitle);
				 composite.add(jFreeChartComposite);
				 if(jFreeChartComposite.isEnable){
					 _selection.add(_items.size() - 1);
				 }
			 }
		 }
		 private synchronized void initList(){
			 if(_list != null){
				 _list.removeAll();
			 }
			 for (String value : _items) { 
				  _list.add(value); 
			  } 
			  if(_selection.size() > 0){
				  intAr  = new int[_selection.size()];
				  for (int i = 0; i < _selection.size(); i++) {
					  intAr[i] = _selection.get(i);
				  }
				  _list.setSelection(intAr);
			  }else{
				  _list.setSelection(-1);
			  }
			  GridData gd = new GridData(GridData.FILL_BOTH); 
			  _list.setLayoutData(gd); 
			  _list.addMouseListener(new MouseAdapter() { 
					 public void mouseUp(MouseEvent event) { 
						 super.mouseUp(event); 
						 intAr = _list.getSelectionIndices(); 
						 if ((event.stateMask & SWT.CTRL) == 0) {
							 resizeParentPanel();
							 renderChareBySelection();
							_floatShell.dispose();
							resetSelect();
					        } 
					 } 
				 }); 
			  _list.addKeyListener(new KeyAdapter() {
				  @Override
				public void keyPressed(KeyEvent e) {
					super.keyPressed(e);
					if (e.keyCode == 13)  
			        {  
						resizeParentPanel();
						renderChareBySelection();
						_floatShell.dispose();
						resetSelect();
						resizeParentPanel();
			        }  
				}
			});
		 }
		 
		 private void renderChareBySelection(){
			 for (int i = 0; i < intAr.length; i++) {
				 try {
					 JFreeChartComposite jFreeChartComposite = composite.get(intAr[i]);
					 if(jFreeChartComposite.isEnable == false){
						 DefaultDiagramJfreeChartAbstract implChart = ChartDiagramConstant.allDiagramImpMap.get(chartKeys.get(intAr[i]));
						 implChart.renderChartByDataSet(true);
					 }
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		 }
		 public synchronized void resetSelect(){
			 resetData();
			 initList();
			 displayText();
			 
		 }
	 }*/