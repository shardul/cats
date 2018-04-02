package github.shardul.cats.di;

import dagger.Component;
import github.shardul.cats.CatsRepository;
import github.shardul.cats.network.CatsService;

/**
 * Created by Shardul on 29/03/18.
 */
@CatsApplicationScope
@Component(modules = {CatsServicesModule.class, CatsDataModule.class})
public interface CatsAppComponent {

    CatsService catsService();

    CatsRepository repository();
}
