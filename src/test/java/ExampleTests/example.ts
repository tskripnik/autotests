import { Actions } from '../../../../../common/helpers/actions';
import { Config } from '../../app/utils/config';
import { header } from '../pages/header-8x';
import { Page } from '../pages/page';
import { AppConfig } from '../utils';
import { loginAsAdministrator } from '../utils/login-utils';

describe('Login to App', () => {
	const host = AppConfig.getHost;
	if (!Config.isPortal) {
		it('should login to App', async () => {
			await loginAsAdministrator();
			if (host === '7x') {
				expect(await Actions.isElementPresentOnPage(page.button)).toBe(true);
			} else {
				expect(await Actions.isElementPresentOnPage(header.userProfile)).toBe(true);
			}
		}, 1800000);
	}
});
