package viewmodel;

import model.GradeListModel;

public class ViewModelFactory
{
  private GradeListViewModel gradeListViewModel;
  private ManageGradeViewModel manageGradeViewModel;
  private DetailsViewModel detailsViewModel;

  public ViewModelFactory(GradeListModel model)
  {
    this.gradeListViewModel = new GradeListViewModel(model);
    this.manageGradeViewModel = new ManageGradeViewModel(model);
    this.detailsViewModel = new DetailsViewModel(model);
  }

  public GradeListViewModel getGradeListViewModel()
  {
    return gradeListViewModel;
  }

  public ManageGradeViewModel getManageGradeViewModel()
  {
    return manageGradeViewModel;
  }

  public DetailsViewModel getDetailsViewModel()
  {
    return detailsViewModel;
  }
}
