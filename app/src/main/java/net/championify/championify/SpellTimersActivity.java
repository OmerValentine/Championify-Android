package net.championify.championify;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class SpellTimersActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private final static int DELAY = 1000;

    private TextToSpeech tts;

    private TextView tvFlashTimer, tvIgniteTimer, tvHealTimer, tvTeleportTimer,
            tvGhostTimer, tvExhaustTimer, tvBarrierTimer, tvCleanseTimer;
    private Button btnFlashCancel, btnIgniteCancel, btnHealCancel, btnTeleportCancel,
            btnGhostCancel, btnExhaustCancel, btnBarrierCancel, btnCleanseCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_timers);

        Locale.setDefault(new Locale("en", "US"));

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        tts = new TextToSpeech(this, this);
        tts.setLanguage(Locale.US);

        tvFlashTimer = (TextView) findViewById(R.id.tv_flash_clock_actspelltimers);
        tvIgniteTimer = (TextView) findViewById(R.id.tv_ignite_clock_actspelltimers);
        tvHealTimer = (TextView) findViewById(R.id.tv_heal_clock_actspelltimers);
        tvTeleportTimer = (TextView) findViewById(R.id.tv_teleport_clock_actspelltimers);
        tvGhostTimer = (TextView) findViewById(R.id.tv_ghost_clock_actspelltimers);
        tvExhaustTimer = (TextView) findViewById(R.id.tv_exhaust_clock_actspelltimers);
        tvBarrierTimer = (TextView) findViewById(R.id.tv_barrier_clock_actspelltimers);
        tvCleanseTimer = (TextView) findViewById(R.id.tv_cleanse_clock_actspelltimers);

        btnFlashCancel = (Button) findViewById(R.id.btn_flashcancel_actspelltimers);
        btnIgniteCancel = (Button) findViewById(R.id.btn_ignitecancel_actspelltimers);
        btnHealCancel = (Button) findViewById(R.id.btn_healcancel_actspelltimers);
        btnTeleportCancel = (Button) findViewById(R.id.btn_teleportcancel_actspelltimers);
        btnGhostCancel = (Button) findViewById(R.id.btn_ghostcancel_actspelltimers);
        btnExhaustCancel = (Button) findViewById(R.id.btn_exhaustcancel_actspelltimers);
        btnBarrierCancel = (Button) findViewById(R.id.btn_barriercancel_actspelltimers);
        btnCleanseCancel = (Button) findViewById(R.id.btn_cleansecancel_actspelltimers);

        btnFlashCancel.setOnClickListener(this);
        btnIgniteCancel.setOnClickListener(this);
        btnHealCancel.setOnClickListener(this);
        btnTeleportCancel.setOnClickListener(this);
        btnGhostCancel.setOnClickListener(this);
        btnExhaustCancel.setOnClickListener(this);
        btnBarrierCancel.setOnClickListener(this);
        btnCleanseCancel.setOnClickListener(this);

        findViewById(R.id.img_flash_actspelltimers).setOnClickListener(this);
        findViewById(R.id.img_ignite_actspelltimers).setOnClickListener(this);
        findViewById(R.id.img_heal_actspelltimers).setOnClickListener(this);
        findViewById(R.id.img_teleport_actspelltimers).setOnClickListener(this);
        findViewById(R.id.img_ghost_actspelltimers).setOnClickListener(this);
        findViewById(R.id.img_exhaust_actspelltimers).setOnClickListener(this);
        findViewById(R.id.img_barrier_actspelltimers).setOnClickListener(this);
        findViewById(R.id.img_cleanse_actspelltimers).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }


    @Override
    public void onClick(View v) {
        final Handler handler = new Handler();

        switch (v.getId()) {
            case R.id.img_flash_actspelltimers:
                tvFlashTimer.setText(String.valueOf(300));

                final Timer timerFlash = new Timer();

                final TimerTask taskFlash = new TimerTask() {
                    private int counter = 300;
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                tvFlashTimer.setText(String.valueOf(counter));
                            }
                        });
                        if(--counter == 0) {
                            timerFlash.cancel();

                            tts.speak("Flash is up", TextToSpeech.QUEUE_ADD, null);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvFlashTimer.setText(String.valueOf("300"));
                                    tvFlashTimer.setVisibility(View.GONE);
                                }
                            });
                        }
                        btnFlashCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timerFlash.cancel();
                                counter = 300;
                                tvFlashTimer.setText(String.valueOf(counter));

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvFlashTimer.setVisibility(View.GONE);
                                    }
                                });
                            }
                        });
                    }
                };

                timerFlash.schedule(taskFlash, DELAY, DELAY);
                tvFlashTimer.setVisibility(View.VISIBLE);
                break;
            case R.id.img_ignite_actspelltimers:
                tvIgniteTimer.setText(String.valueOf(210));

                final Timer timerIgnite = new Timer();

                final TimerTask taskIgnite = new TimerTask() {
                    private int counter = 210;
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                tvIgniteTimer.setText(String.valueOf(counter));
                            }
                        });
                        if(--counter == 0) {
                            timerIgnite.cancel();

                            tts.speak("Ignite is up", TextToSpeech.QUEUE_ADD, null);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvIgniteTimer.setVisibility(View.GONE);
                                }
                            });
                        }
                        btnIgniteCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timerIgnite.cancel();
                                counter = 210;
                                tvIgniteTimer.setText(String.valueOf(counter));

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvIgniteTimer.setText(String.valueOf("210"));
                                        tvIgniteTimer.setVisibility(View.GONE);
                                    }
                                });
                            }
                        });
                    }
                };

                timerIgnite.schedule(taskIgnite, DELAY, DELAY);
                tvIgniteTimer.setVisibility(View.VISIBLE);
                break;
            case R.id.img_heal_actspelltimers:
                tvHealTimer.setText(String.valueOf(240));

                final Timer timerHeal = new Timer();

                final TimerTask taskHeal = new TimerTask() {
                    private int counter = 240;
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                tvHealTimer.setText(String.valueOf(counter));
                            }
                        });
                        if(--counter == 0) {
                            timerHeal.cancel();

                            tts.speak("Heal is up", TextToSpeech.QUEUE_ADD, null);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvHealTimer.setVisibility(View.GONE);
                                }
                            });
                        }
                        btnHealCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timerHeal.cancel();
                                counter = 240;
                                tvHealTimer.setText(String.valueOf(counter));

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvHealTimer.setText(String.valueOf("240"));
                                        tvHealTimer.setVisibility(View.GONE);
                                    }
                                });
                            }
                        });
                    }
                };

                timerHeal.schedule(taskHeal, DELAY, DELAY);
                tvHealTimer.setVisibility(View.VISIBLE);
                break;
            case R.id.img_teleport_actspelltimers:
                tvTeleportTimer.setText(String.valueOf(300));

                final Timer timerTeleport = new Timer();

                final TimerTask taskTeleport = new TimerTask() {
                    private int counter = 300;
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                tvTeleportTimer.setText(String.valueOf(counter));
                            }
                        });
                        if(--counter == 0) {
                            timerTeleport.cancel();

                            tts.speak("Teleport is up", TextToSpeech.QUEUE_ADD, null);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvTeleportTimer.setVisibility(View.GONE);
                                }
                            });
                        }
                        btnTeleportCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timerTeleport.cancel();
                                counter = 300;
                                tvTeleportTimer.setText(String.valueOf(counter));

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvTeleportTimer.setText(String.valueOf("300"));
                                        tvTeleportTimer.setVisibility(View.GONE);
                                    }
                                });
                            }
                        });
                    }
                };

                timerTeleport.schedule(taskTeleport, DELAY, DELAY);
                tvTeleportTimer.setVisibility(View.VISIBLE);
                break;
            case R.id.img_ghost_actspelltimers:
                tvGhostTimer.setText(String.valueOf(180));

                final Timer timerGhost = new Timer();

                final TimerTask taskGhost = new TimerTask() {
                    private int counter = 180;
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                tvGhostTimer.setText(String.valueOf(counter));
                            }
                        });
                        if(--counter == 0) {
                            timerGhost.cancel();

                            tts.speak("Ghost is up", TextToSpeech.QUEUE_ADD, null);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvGhostTimer.setVisibility(View.GONE);
                                }
                            });
                        }
                        btnGhostCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timerGhost.cancel();
                                counter = 180;
                                tvGhostTimer.setText(String.valueOf(counter));

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvGhostTimer.setText(String.valueOf("180"));
                                        tvGhostTimer.setVisibility(View.GONE);
                                    }
                                });
                            }
                        });
                    }
                };

                timerGhost.schedule(taskGhost, DELAY, DELAY);
                tvGhostTimer.setVisibility(View.VISIBLE);
                break;
            case R.id.img_exhaust_actspelltimers:
                tvExhaustTimer.setText(String.valueOf(210));

                final Timer timerExhaust = new Timer();

                final TimerTask taskExhaust = new TimerTask() {
                    private int counter = 210;
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                tvExhaustTimer.setText(String.valueOf(counter));
                            }
                        });
                        if(--counter == 0) {
                            timerExhaust.cancel();

                            tts.speak("Exhaust is up", TextToSpeech.QUEUE_ADD, null);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvExhaustTimer.setVisibility(View.GONE);
                                }
                            });
                        }
                        btnExhaustCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timerExhaust.cancel();
                                counter = 210;
                                tvExhaustTimer.setText(String.valueOf(counter));

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvExhaustTimer.setText(String.valueOf("210"));
                                        tvExhaustTimer.setVisibility(View.GONE);
                                    }
                                });
                            }
                        });
                    }
                };

                timerExhaust.schedule(taskExhaust, DELAY, DELAY);
                tvExhaustTimer.setVisibility(View.VISIBLE);
                break;
            case R.id.img_barrier_actspelltimers:
                tvBarrierTimer.setText(String.valueOf(180));

                final Timer timerBarrier = new Timer();

                final TimerTask taskBarrier = new TimerTask() {
                    private int counter = 180;
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                tvBarrierTimer.setText(String.valueOf(counter));
                            }
                        });
                        if(--counter == 0) {
                            timerBarrier.cancel();

                            tts.speak("Barrier is up", TextToSpeech.QUEUE_ADD, null);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvBarrierTimer.setVisibility(View.GONE);
                                }
                            });
                        }
                        btnBarrierCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timerBarrier.cancel();
                                counter = 180;
                                tvBarrierTimer.setText(String.valueOf(counter));

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvBarrierTimer.setText(String.valueOf("180"));
                                        tvBarrierTimer.setVisibility(View.GONE);
                                    }
                                });
                            }
                        });
                    }
                };

                timerBarrier.schedule(taskBarrier, DELAY, DELAY);
                tvBarrierTimer.setVisibility(View.VISIBLE);
                break;
            case R.id.img_cleanse_actspelltimers:
                tvCleanseTimer.setText(String.valueOf(210));

                final Timer timerCleanse = new Timer();

                final TimerTask taskCleanse = new TimerTask() {
                    private int counter = 210;
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                tvCleanseTimer.setText(String.valueOf(counter));
                            }
                        });
                        if(--counter == 0) {
                            timerCleanse.cancel();

                            tts.speak("Cleanse is up", TextToSpeech.QUEUE_ADD, null);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvCleanseTimer.setVisibility(View.GONE);
                                }
                            });
                        }
                        btnCleanseCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timerCleanse.cancel();
                                counter = 210;
                                tvCleanseTimer.setText(String.valueOf(counter));

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvCleanseTimer.setText(String.valueOf("210"));
                                        tvCleanseTimer.setVisibility(View.GONE);
                                    }
                                });
                            }
                        });
                    }
                };

                timerCleanse.schedule(taskCleanse, DELAY, DELAY);
                tvCleanseTimer.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onInit(int status) {

    }
}
