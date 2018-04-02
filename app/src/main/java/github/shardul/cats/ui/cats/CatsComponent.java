package github.shardul.cats.ui.cats;

import dagger.Component;
import github.shardul.cats.di.CatsAppComponent;

/**
 * Created by Shardul on 31/03/18.
 */
@CatsFragmentScope
@Component(dependencies = {CatsAppComponent.class})
public interface CatsComponent {

    public void inject(CatsFragment fragment);
}
