# coding:utf-8
import pymc3 as pm
import matplotlib.pyplot as plt
print(pm.__version__)

if __name__ == '__main__':
    n1 = 30938
    n2 = 15438
    obs_v1 = n1*0.3612
    obs_v2 = n2*0.3678

    with pm.Model() as model:  # context management
        # define priors
        prior_v1 = pm.Beta('prior_v1', alpha=2, beta=2)
        prior_v2 = pm.Beta('prior_v2', alpha=2, beta=2)

        # define likelihood
        like_v1 = pm.Binomial('like_v1', n=n1, p=prior_v1, observed=obs_v1)
        like_v2 = pm.Binomial('like_v2', n=n2, p=prior_v2, observed=obs_v2)

        # define metrics
        pm.Deterministic('difference', prior_v2 - prior_v1)
        pm.Deterministic('relation', (prior_v2 / prior_v1) - 1)
        # inference
        print('sample!')
        trace = pm.sample(draws=50000, step=pm.Metropolis(), start=pm.find_MAP(), progressbar=True)
        _ = pm.traceplot(trace[1000:])
        _ = pm.plot_posterior(trace[1000:], varnames=['difference', 'relation'],
                              ref_val=0, color='#87ceeb')
        plt.show()

    print('Done!')
